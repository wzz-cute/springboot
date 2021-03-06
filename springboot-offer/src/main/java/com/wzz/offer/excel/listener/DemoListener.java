package com.wzz.offer.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.wzz.offer.category.entity.CategoryEntity;
import com.wzz.offer.category.service.CategoryService;
import org.apache.commons.lang.StringUtils;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 有个很重要的点 DocumentListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
 *
 * @author crush
 */
public class DemoListener extends AnalysisEventListener<CategoryEntity> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoListener.class);
    ExecutorService executor = Executors.newFixedThreadPool(20);


    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;

    List<CategoryEntity> list = new ArrayList<CategoryEntity>();

    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */
    private CategoryService categoryService;

//    public DemoListener() {
//        // 这里是demo，所以随便new一个。实际使用如果到了spring,请使用下面的有参构造函数
//    }

    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     *
     * @param
     */
    public DemoListener(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(CategoryEntity data, AnalysisContext context) {
//        if (!isAllNull(data)) {
//            System.out.println("解析到一条数据:" + JSON.toJSONString(data));
        list.add(data);
//        }
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() == BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list.clear();
        }

    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        //进行数据库层面操作
        String savePath = "C:\\Users\\86180\\Desktop\\内鬼数据.txt";

        /**
         * 没有线程池
         * Fri Mar 11 14:12:53 CST 2022
         * Fri Mar 11 14:13:43 CST 2022
         */
//            for (CategoryEntity categoryEntity : list) {
//                BufferedWriter bufferedWriter = null;
//                try {
//                    bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(savePath, true)));
//                    synchronized (CategoryEntity.class) {
////                                    System.out.println(Thread.currentThread().getId() + "  " + Thread.currentThread().getName());
//                        String s = categoryEntity.toString();
////                                    System.out.println(s);
//                        bufferedWriter.write(s);
//                        bufferedWriter.write("\r\n");
//                        bufferedWriter.flush();
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } finally {
//                    if (bufferedWriter != null) {
//                        try {
//                            bufferedWriter.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }

        for (CategoryEntity categoryEntity : list) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    BufferedWriter bufferedWriter = null;
                    try {
                        bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(savePath, true)));
//                        synchronized (CategoryEntity.class) {
//                                    System.out.println(Thread.currentThread().getId() + "  " + Thread.currentThread().getName());
                        String s = categoryEntity.toString();
//                                    System.out.println(s);
                        bufferedWriter.write(s);
                        bufferedWriter.write("\r\n");
                        bufferedWriter.flush();
//                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (bufferedWriter != null) {
                            try {
                                bufferedWriter.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
        }
    }

    private boolean isAllNull(CategoryEntity t) {
        Class<? extends CategoryEntity> aClass = t.getClass();
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            if (method.getName().contains("get") && method.getParameterCount() == 0) {
                if (!StringUtils.contains(method.getName(), "getCellStyleMap") && !StringUtils.contains(method.getName(), "getClass")) {
                    Object invoke = null;
                    try {
                        invoke = method.invoke(t);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (invoke == null) {
                        return false;
                    }
                }

            }
        }
        return true;
    }
}

