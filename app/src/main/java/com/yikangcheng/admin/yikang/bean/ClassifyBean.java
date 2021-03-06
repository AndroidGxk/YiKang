package com.yikangcheng.admin.yikang.bean;

import java.util.List;

/**
 * Created by lenovo on 2019/5/17.
 * WF
 */
public class ClassifyBean {
    @Override
    public String toString() {
        return "ClassifyBean{" +
                "message='" + message + '\'' +
                ", success=" + success +
                ", entity=" + entity +
                '}';
    }

    /**
     * message : 查询成功
     * success : true
     * entity : [{"subjectId":449,"subjectName":"手机数码","status":0,"createTime":"2019-04-18 17:42:03","updateTime":"2019-04-18 17:42:03","parentId":0,"level":1,"image":"","sort":15,"childSubjectList":[{"subjectId":450,"subjectName":"手机","status":0,"createTime":"2019-04-18 17:42:26","updateTime":"2019-04-18 17:42:26","parentId":449,"level":2,"image":"","sort":0,"childSubjectList":[{"subjectId":456,"subjectName":"手机","status":0,"createTime":"2019-04-18 17:44:36","updateTime":"2019-04-29 11:08:03","parentId":450,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556507281432560448.jpg","sort":0,"childSubjectList":[]},{"subjectId":457,"subjectName":"手机配件","status":0,"createTime":"2019-04-18 17:44:55","updateTime":"2019-04-29 11:08:17","parentId":450,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556507296387922382.jpg","sort":0,"childSubjectList":[]}]},{"subjectId":452,"subjectName":"数码产品","status":0,"createTime":"2019-04-18 17:42:42","updateTime":"2019-04-18 17:45:34","parentId":449,"level":2,"image":"","sort":0,"childSubjectList":[{"subjectId":458,"subjectName":"耳机","status":0,"createTime":"2019-04-18 17:45:06","updateTime":"2019-04-30 09:23:34","parentId":452,"level":3,"image":"","icon":"/upload/mavendemo/course/20190430/1556587411373285427.jpg","sort":0,"childSubjectList":[]},{"subjectId":459,"subjectName":"音响","status":0,"createTime":"2019-04-18 17:45:45","updateTime":"2019-04-30 09:23:47","parentId":452,"level":3,"image":"","icon":"/upload/mavendemo/course/20190430/1556587424317382120.jpg","sort":0,"childSubjectList":[]},{"subjectId":460,"subjectName":"无人机","status":0,"createTime":"2019-04-18 17:45:54","updateTime":"2019-04-30 09:24:03","parentId":452,"level":3,"image":"","icon":"/upload/mavendemo/course/20190430/1556587440077306294.jpg","sort":0,"childSubjectList":[]},{"subjectId":461,"subjectName":"摄影自拍","status":0,"createTime":"2019-04-18 17:46:09","updateTime":"2019-04-30 09:24:19","parentId":452,"level":3,"image":"","icon":"/upload/mavendemo/course/20190430/1556587457687791315.jpg","sort":0,"childSubjectList":[]},{"subjectId":462,"subjectName":"智能手表","status":0,"createTime":"2019-04-18 18:00:00","updateTime":"2019-04-30 09:24:35","parentId":452,"level":3,"image":"","icon":"/upload/mavendemo/course/20190430/1556587473747989433.jpg","sort":0,"childSubjectList":[]}]}]},{"subjectId":469,"subjectName":"电脑办公","status":0,"createTime":"2019-04-19 10:58:51","updateTime":"2019-05-06 11:02:28","parentId":0,"level":1,"image":"","icon":"","sort":13,"childSubjectList":[{"subjectId":470,"subjectName":"电脑","status":0,"createTime":"2019-04-19 10:59:15","updateTime":"2019-04-19 10:59:15","parentId":469,"level":2,"image":"","sort":0,"childSubjectList":[{"subjectId":471,"subjectName":"笔记本","status":0,"createTime":"2019-04-19 10:59:34","updateTime":"2019-05-06 11:01:25","parentId":470,"level":3,"image":"","icon":"/upload/mavendemo/course/20190430/1556588168014881743.jpg","sort":0,"childSubjectList":[]},{"subjectId":472,"subjectName":"平板电脑","status":0,"createTime":"2019-04-19 11:00:29","updateTime":"2019-04-30 09:36:20","parentId":470,"level":3,"image":"","icon":"/upload/mavendemo/course/20190430/1556588178848846649.jpg","sort":0,"childSubjectList":[]}]},{"subjectId":473,"subjectName":"办公用品","status":0,"createTime":"2019-04-19 11:01:34","updateTime":"2019-04-19 11:01:34","parentId":469,"level":2,"image":"","sort":0,"childSubjectList":[{"subjectId":474,"subjectName":"笔类","status":0,"createTime":"2019-04-19 11:01:50","updateTime":"2019-04-30 09:36:38","parentId":473,"level":3,"image":"","icon":"/upload/mavendemo/course/20190430/1556588196771801978.jpg","sort":0,"childSubjectList":[]},{"subjectId":475,"subjectName":"擦涂修改","status":0,"createTime":"2019-04-19 11:02:18","updateTime":"2019-04-30 09:36:54","parentId":473,"level":3,"image":"","icon":"/upload/mavendemo/course/20190430/1556588212491763975.jpg","sort":0,"childSubjectList":[]},{"subjectId":479,"subjectName":"音频视频","status":0,"createTime":"2019-04-19 13:47:44","updateTime":"2019-05-06 11:01:47","parentId":473,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556515084518859285.png","sort":0,"childSubjectList":[]}]}]},{"subjectId":48,"subjectName":"家用电器","status":0,"createTime":"2019-03-15 10:26:51","updateTime":"2019-04-19 13:42:18","parentId":0,"level":1,"image":"","sort":12,"childSubjectList":[{"subjectId":376,"subjectName":"厨房电器","status":0,"createTime":"2019-03-27 13:48:53","updateTime":"2019-03-28 11:06:20","parentId":48,"level":2,"image":"","sort":6,"childSubjectList":[{"subjectId":384,"subjectName":"电饭煲","status":0,"createTime":"2019-03-27 13:51:36","updateTime":"2019-04-29 11:12:58","parentId":376,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556507577217853957.jpg","sort":0,"childSubjectList":[]},{"subjectId":385,"subjectName":"电压力锅","status":0,"createTime":"2019-03-27 13:52:00","updateTime":"2019-04-29 11:13:15","parentId":376,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556507593711169945.jpg","sort":0,"childSubjectList":[]},{"subjectId":480,"subjectName":"厨房小电","status":0,"createTime":"2019-04-19 14:06:19","updateTime":"2019-04-29 11:14:03","parentId":376,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556507641534632019.jpg","sort":0,"childSubjectList":[]}]},{"subjectId":57,"subjectName":"生活电器","status":0,"createTime":"2019-03-15 10:31:50","updateTime":"2019-03-28 11:06:51","parentId":48,"level":2,"image":"","sort":2,"childSubjectList":[{"subjectId":438,"subjectName":"洗衣机","status":0,"createTime":"2019-04-10 09:09:25","updateTime":"2019-04-29 11:14:27","parentId":57,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556507661814804395.jpg","sort":0,"childSubjectList":[]},{"subjectId":440,"subjectName":"电视机","status":0,"createTime":"2019-04-10 09:09:52","updateTime":"2019-04-29 11:14:43","parentId":57,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556507681720200038.jpg","sort":0,"childSubjectList":[]},{"subjectId":218,"subjectName":"电吹风","status":0,"createTime":"2019-03-21 17:13:32","updateTime":"2019-04-29 11:15:02","parentId":57,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556507700215876154.jpg","sort":0,"childSubjectList":[]},{"subjectId":219,"subjectName":"吸尘器","status":0,"createTime":"2019-03-21 17:15:13","updateTime":"2019-04-29 11:15:53","parentId":57,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556507751804757012.png","sort":0,"childSubjectList":[]},{"subjectId":220,"subjectName":"电水壶","status":0,"createTime":"2019-03-21 17:16:30","updateTime":"2019-04-29 11:16:50","parentId":57,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556507808600028551.png","sort":0,"childSubjectList":[]},{"subjectId":482,"subjectName":"生活电器","status":0,"createTime":"2019-04-19 14:14:37","updateTime":"2019-04-29 11:17:13","parentId":57,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556507831638135420.png","sort":0,"childSubjectList":[]},{"subjectId":486,"subjectName":"个护清洁","status":0,"createTime":"2019-04-20 13:24:15","updateTime":"2019-04-29 16:50:45","parentId":57,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556527841626636978.jpg","sort":0,"childSubjectList":[]}]}]},{"subjectId":7,"subjectName":"美妆个护","status":0,"createTime":"2019-03-04 10:23:11","updateTime":"2019-03-28 11:03:50","parentId":0,"level":1,"image":"/upload/mavendemo/course/20190305/1551748070337173016.jpeg","sort":10,"childSubjectList":[{"subjectId":257,"subjectName":"彩妆","status":0,"createTime":"2019-03-22 13:35:16","updateTime":"2019-03-28 11:07:38","parentId":7,"level":2,"image":"","sort":2,"childSubjectList":[{"subjectId":261,"subjectName":"唇膏唇釉","status":0,"createTime":"2019-03-22 13:41:59","updateTime":"2019-04-29 11:18:52","parentId":257,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556507931061481387.png","sort":0,"childSubjectList":[]},{"subjectId":263,"subjectName":"修容定妆","status":0,"createTime":"2019-03-22 13:42:30","updateTime":"2019-04-29 11:19:16","parentId":257,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556507955163246810.png","sort":0,"childSubjectList":[]},{"subjectId":264,"subjectName":"彩妆工具","status":0,"createTime":"2019-03-22 13:42:39","updateTime":"2019-04-29 11:20:34","parentId":257,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556508032030406732.png","sort":0,"childSubjectList":[]},{"subjectId":265,"subjectName":"美瞳美睫","status":0,"createTime":"2019-03-22 13:43:58","updateTime":"2019-04-29 16:51:17","parentId":257,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556527874700395737.jpg","sort":0,"childSubjectList":[]},{"subjectId":275,"subjectName":"隔离防晒","status":0,"createTime":"2019-03-22 14:33:03","updateTime":"2019-04-29 16:51:39","parentId":257,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556527897054289095.jpg","sort":0,"childSubjectList":[]},{"subjectId":300,"subjectName":"腮红","status":0,"createTime":"2019-03-22 16:50:03","updateTime":"2019-04-29 11:22:20","parentId":257,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556508138326849208.png","sort":0,"childSubjectList":[]}]},{"subjectId":55,"subjectName":"护肤","status":0,"createTime":"2019-03-15 10:30:11","updateTime":"2019-03-28 11:07:16","parentId":7,"level":2,"image":"/upload/mavendemo/course/20190317/1552835827006141209.jpg","sort":2,"childSubjectList":[{"subjectId":259,"subjectName":"面膜/唇膜","status":0,"createTime":"2019-03-22 13:41:03","updateTime":"2019-04-29 16:52:03","parentId":55,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556527921600181506.jpg","sort":0,"childSubjectList":[]},{"subjectId":260,"subjectName":"爽肤水","status":0,"createTime":"2019-03-22 13:41:11","updateTime":"2019-04-29 11:23:34","parentId":55,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556508213016592562.png","sort":0,"childSubjectList":[]},{"subjectId":85,"subjectName":"面霜眼霜","status":0,"createTime":"2019-03-21 13:19:52","updateTime":"2019-04-29 11:23:58","parentId":55,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556508236557498923.png","sort":0,"childSubjectList":[]},{"subjectId":86,"subjectName":"洗面/洁面","status":0,"createTime":"2019-03-21 13:20:28","updateTime":"2019-04-29 11:24:20","parentId":55,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556508259494930772.png","sort":0,"childSubjectList":[]},{"subjectId":491,"subjectName":"护肤单品","status":0,"createTime":"2019-05-09 11:32:06","updateTime":"2019-05-10 16:14:49","parentId":55,"level":3,"image":"","icon":"/upload/mavendemo/course/20190510/1557476087842987513.jpeg","sort":0,"childSubjectList":[]},{"subjectId":253,"subjectName":"护肤套装","status":0,"createTime":"2019-03-22 11:29:15","updateTime":"2019-04-29 11:24:44","parentId":55,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556508282489038176.png","sort":0,"childSubjectList":[]},{"subjectId":255,"subjectName":"美容器材","status":0,"createTime":"2019-03-22 13:10:26","updateTime":"2019-04-29 11:25:11","parentId":55,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556508309002685188.png","sort":0,"childSubjectList":[]}]},{"subjectId":293,"subjectName":"香水/香氛","status":0,"createTime":"2019-03-22 16:33:01","updateTime":"2019-03-28 11:07:29","parentId":7,"level":2,"image":"","sort":1,"childSubjectList":[{"subjectId":294,"subjectName":"香水香氛","status":0,"createTime":"2019-03-22 16:33:13","updateTime":"2019-04-29 11:25:41","parentId":293,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556508339682442306.png","sort":0,"childSubjectList":[]}]},{"subjectId":56,"subjectName":"洗发护发","status":0,"createTime":"2019-03-15 10:30:16","updateTime":"2019-03-28 11:07:49","parentId":7,"level":2,"image":"/upload/mavendemo/course/20190317/1552835843338416015.jpg","sort":1,"childSubjectList":[{"subjectId":87,"subjectName":"洗发露","status":0,"createTime":"2019-03-21 13:20:53","updateTime":"2019-04-29 11:25:59","parentId":56,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556508358643801295.png","sort":0,"childSubjectList":[]},{"subjectId":89,"subjectName":"美发工具","status":0,"createTime":"2019-03-21 13:21:30","updateTime":"2019-04-29 11:26:25","parentId":56,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556508383945343707.png","sort":0,"childSubjectList":[]}]},{"subjectId":242,"subjectName":"口腔护理","status":0,"createTime":"2019-03-21 17:31:44","updateTime":"2019-03-21 17:31:44","parentId":7,"level":2,"image":"","sort":0,"childSubjectList":[{"subjectId":245,"subjectName":"口腔清洁","status":0,"createTime":"2019-03-21 17:34:07","updateTime":"2019-04-29 11:28:12","parentId":242,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556508489517980272.png","sort":0,"childSubjectList":[]}]},{"subjectId":243,"subjectName":"身体护理","status":0,"createTime":"2019-03-21 17:32:02","updateTime":"2019-03-21 17:32:02","parentId":7,"level":2,"image":"","sort":0,"childSubjectList":[{"subjectId":246,"subjectName":"沐浴用品","status":0,"createTime":"2019-03-21 17:38:11","updateTime":"2019-04-29 16:54:18","parentId":243,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556527952635532574.jpg","sort":0,"childSubjectList":[]}]}]},{"subjectId":25,"subjectName":"箱包钟表","status":0,"createTime":"2019-03-15 10:18:23","updateTime":"2019-04-20 16:37:06","parentId":0,"level":1,"image":"","sort":10,"childSubjectList":[{"subjectId":58,"subjectName":"钱包","status":0,"createTime":"2019-03-15 11:10:15","updateTime":"2019-03-28 11:08:36","parentId":25,"level":2,"image":"","sort":1,"childSubjectList":[{"subjectId":208,"subjectName":"男士钱包","status":0,"createTime":"2019-03-21 17:08:33","updateTime":"2019-04-29 11:29:17","parentId":58,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556508548241530940.png","sort":0,"childSubjectList":[]},{"subjectId":209,"subjectName":"女士钱包","status":0,"createTime":"2019-03-21 17:08:44","updateTime":"2019-04-29 11:29:45","parentId":58,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556508583308097166.png","sort":0,"childSubjectList":[]}]},{"subjectId":26,"subjectName":"女包","status":0,"createTime":"2019-03-15 10:18:44","updateTime":"2019-03-15 10:18:44","parentId":25,"level":2,"image":"","sort":0,"childSubjectList":[{"subjectId":193,"subjectName":"手提包","status":0,"createTime":"2019-03-21 17:01:50","updateTime":"2019-04-29 11:30:15","parentId":26,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556508613600614549.png","sort":0,"childSubjectList":[]},{"subjectId":194,"subjectName":"单肩包","status":0,"createTime":"2019-03-21 17:02:08","updateTime":"2019-04-29 11:31:10","parentId":26,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556508668538681010.png","sort":0,"childSubjectList":[]},{"subjectId":195,"subjectName":"手包","status":0,"createTime":"2019-03-21 17:02:23","updateTime":"2019-04-29 11:32:18","parentId":26,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556508736545167612.png","sort":0,"childSubjectList":[]}]},{"subjectId":27,"subjectName":"男包","status":0,"createTime":"2019-03-15 10:18:50","updateTime":"2019-03-15 10:18:50","parentId":25,"level":2,"image":"","sort":0,"childSubjectList":[{"subjectId":196,"subjectName":"手提包","status":0,"createTime":"2019-03-21 17:02:35","updateTime":"2019-04-30 09:28:03","parentId":27,"level":3,"image":"","icon":"/upload/mavendemo/course/20190430/1556587682195226618.jpg","sort":0,"childSubjectList":[]},{"subjectId":197,"subjectName":"手包","status":0,"createTime":"2019-03-21 17:02:48","updateTime":"2019-04-29 11:33:58","parentId":27,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556508837147892004.png","sort":0,"childSubjectList":[]},{"subjectId":198,"subjectName":"单肩包","status":0,"createTime":"2019-03-21 17:03:01","updateTime":"2019-05-06 10:56:27","parentId":27,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556508889585081202.png","sort":0,"childSubjectList":[]}]},{"subjectId":28,"subjectName":"功能箱包","status":0,"createTime":"2019-03-15 10:19:05","updateTime":"2019-03-15 10:19:05","parentId":25,"level":2,"image":"","sort":0,"childSubjectList":[{"subjectId":199,"subjectName":"行李箱","status":0,"createTime":"2019-03-21 17:03:34","updateTime":"2019-05-06 10:56:06","parentId":28,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556508929363897363.png","sort":0,"childSubjectList":[]},{"subjectId":200,"subjectName":"双肩包","status":0,"createTime":"2019-03-21 17:04:12","updateTime":"2019-04-29 11:36:30","parentId":28,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556508988455469732.png","sort":0,"childSubjectList":[]}]},{"subjectId":29,"subjectName":"钟表","status":0,"createTime":"2019-03-15 10:19:22","updateTime":"2019-03-15 10:19:22","parentId":25,"level":2,"image":"","sort":0,"childSubjectList":[{"subjectId":407,"subjectName":"儿童手表","status":0,"createTime":"2019-03-27 15:27:10","updateTime":"2019-04-30 09:25:23","parentId":29,"level":3,"image":"","icon":"/upload/mavendemo/course/20190430/1556587522342810250.jpg","sort":0,"childSubjectList":[]},{"subjectId":203,"subjectName":"男士腕表","status":0,"createTime":"2019-03-21 17:07:04","updateTime":"2019-04-29 12:30:29","parentId":29,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556512226472004676.png","sort":0,"childSubjectList":[]},{"subjectId":204,"subjectName":"女士腕表","status":0,"createTime":"2019-03-21 17:07:20","updateTime":"2019-04-29 12:30:55","parentId":29,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556512253644843481.png","sort":0,"childSubjectList":[]}]}]},{"subjectId":8,"subjectName":"服装鞋帽","status":0,"createTime":"2019-03-04 10:23:42","updateTime":"2019-03-28 11:02:22","parentId":0,"level":1,"image":"/upload/mavendemo/course/20190305/1551772551327938038.jpeg","sort":9,"childSubjectList":[{"subjectId":41,"subjectName":"男装","status":0,"createTime":"2019-03-15 10:23:23","updateTime":"2019-03-28 11:08:51","parentId":8,"level":2,"image":"","sort":1,"childSubjectList":[{"subjectId":93,"subjectName":"春夏爆款","status":0,"createTime":"2019-03-21 13:24:28","updateTime":"2019-04-30 09:26:11","parentId":41,"level":3,"image":"","icon":"/upload/mavendemo/course/20190430/1556587568762249400.jpg","sort":0,"childSubjectList":[]},{"subjectId":97,"subjectName":"热卖男装","status":0,"createTime":"2019-03-21 13:25:45","updateTime":"2019-04-30 09:26:29","parentId":41,"level":3,"image":"","icon":"/upload/mavendemo/course/20190430/1556587588130211418.jpg","sort":0,"childSubjectList":[]}]},{"subjectId":42,"subjectName":"女装","status":0,"createTime":"2019-03-15 10:23:28","updateTime":"2019-03-28 11:09:05","parentId":8,"level":2,"image":"","sort":1,"childSubjectList":[{"subjectId":100,"subjectName":"春夏爆款","status":0,"createTime":"2019-03-21 13:26:50","updateTime":"2019-04-30 09:27:31","parentId":42,"level":3,"image":"","icon":"/upload/mavendemo/course/20190430/1556587649416654246.jpg","sort":0,"childSubjectList":[]},{"subjectId":101,"subjectName":"裙装/裙裤","status":0,"createTime":"2019-03-21 13:27:14","updateTime":"2019-04-29 12:31:50","parentId":42,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556512308169554183.png","sort":0,"childSubjectList":[]},{"subjectId":104,"subjectName":"T恤","status":0,"createTime":"2019-03-21 13:28:58","updateTime":"2019-04-29 12:32:48","parentId":42,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556512364511921082.png","sort":0,"childSubjectList":[]},{"subjectId":437,"subjectName":"夏季新品","status":0,"createTime":"2019-04-08 15:54:48","updateTime":"2019-04-30 09:27:11","parentId":42,"level":3,"image":"","icon":"/upload/mavendemo/course/20190430/1556587629981629970.jpg","sort":0,"childSubjectList":[]}]},{"subjectId":77,"subjectName":"男鞋","status":0,"createTime":"2019-03-19 12:57:15","updateTime":"2019-03-28 11:09:22","parentId":8,"level":2,"image":"","sort":1,"childSubjectList":[{"subjectId":113,"subjectName":"皮鞋","status":0,"createTime":"2019-03-21 14:39:28","updateTime":"2019-04-29 12:40:30","parentId":77,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556512829041836153.png","sort":0,"childSubjectList":[]},{"subjectId":114,"subjectName":"休闲鞋","status":0,"createTime":"2019-03-21 14:39:55","updateTime":"2019-04-29 12:40:57","parentId":77,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556512855559388463.png","sort":0,"childSubjectList":[]},{"subjectId":115,"subjectName":"运动鞋","status":0,"createTime":"2019-03-21 14:40:03","updateTime":"2019-04-29 12:41:24","parentId":77,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556512879987031412.png","sort":0,"childSubjectList":[]}]},{"subjectId":105,"subjectName":"女鞋","status":0,"createTime":"2019-03-21 13:51:24","updateTime":"2019-03-28 11:09:37","parentId":8,"level":2,"image":"","sort":1,"childSubjectList":[{"subjectId":106,"subjectName":"运动鞋","status":0,"createTime":"2019-03-21 13:51:37","updateTime":"2019-04-30 09:28:31","parentId":105,"level":3,"image":"","icon":"/upload/mavendemo/course/20190430/1556587708741468103.jpg","sort":0,"childSubjectList":[]},{"subjectId":432,"subjectName":"单鞋","status":0,"createTime":"2019-04-02 16:02:54","updateTime":"2019-04-29 12:45:23","parentId":105,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556513121718827134.png","sort":0,"childSubjectList":[]},{"subjectId":485,"subjectName":"春夏新品","status":0,"createTime":"2019-04-19 16:58:10","updateTime":"2019-04-29 12:45:59","parentId":105,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556513156763276481.png","sort":0,"childSubjectList":[]}]},{"subjectId":276,"subjectName":"内衣","status":0,"createTime":"2019-03-22 14:36:22","updateTime":"2019-03-22 14:36:22","parentId":8,"level":2,"image":"","sort":0,"childSubjectList":[{"subjectId":277,"subjectName":"男士内衣","status":0,"createTime":"2019-03-22 14:36:37","updateTime":"2019-04-30 09:29:44","parentId":276,"level":3,"image":"","icon":"/upload/mavendemo/course/20190430/1556587782747782284.jpg","sort":0,"childSubjectList":[]},{"subjectId":279,"subjectName":"女士内衣","status":0,"createTime":"2019-03-22 14:37:04","updateTime":"2019-04-30 09:29:58","parentId":276,"level":3,"image":"","icon":"/upload/mavendemo/course/20190430/1556587796916373677.jpg","sort":0,"childSubjectList":[]},{"subjectId":284,"subjectName":"男女睡衣","status":0,"createTime":"2019-03-22 14:57:52","updateTime":"2019-04-30 09:30:29","parentId":276,"level":3,"image":"","icon":"/upload/mavendemo/course/20190430/1556587827997317015.jpg","sort":0,"childSubjectList":[]}]},{"subjectId":82,"subjectName":"童装","status":0,"createTime":"2019-03-20 12:37:30","updateTime":"2019-03-20 12:37:30","parentId":8,"level":2,"image":"","sort":0,"childSubjectList":[{"subjectId":251,"subjectName":"男童","status":0,"createTime":"2019-03-21 17:50:02","updateTime":"2019-04-30 19:19:27","parentId":82,"level":3,"image":"","icon":"/upload/mavendemo/course/20190430/1556623165264375775.jpg","sort":0,"childSubjectList":[]},{"subjectId":252,"subjectName":"女童","status":0,"createTime":"2019-03-21 17:50:11","updateTime":"2019-04-29 12:50:13","parentId":82,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556513411795858855.png","sort":0,"childSubjectList":[]}]},{"subjectId":363,"subjectName":"童鞋","status":0,"createTime":"2019-03-27 11:25:56","updateTime":"2019-03-27 11:25:56","parentId":8,"level":2,"image":"","sort":0,"childSubjectList":[]}]},{"subjectId":38,"subjectName":"服饰配饰","status":0,"createTime":"2019-03-15 10:21:55","updateTime":"2019-03-28 11:02:28","parentId":0,"level":1,"image":"","sort":8,"childSubjectList":[{"subjectId":78,"subjectName":"眼镜/配件","status":0,"createTime":"2019-03-19 13:35:15","updateTime":"2019-03-28 11:13:03","parentId":38,"level":2,"image":"","sort":1,"childSubjectList":[{"subjectId":178,"subjectName":"太阳镜","status":0,"createTime":"2019-03-21 16:50:12","updateTime":"2019-04-29 12:50:49","parentId":78,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556513447098228591.png","sort":0,"childSubjectList":[]},{"subjectId":179,"subjectName":"眼镜镜架","status":0,"createTime":"2019-03-21 16:51:05","updateTime":"2019-04-30 09:30:53","parentId":78,"level":3,"image":"","icon":"/upload/mavendemo/course/20190430/1556587852131230390.jpg","sort":0,"childSubjectList":[]}]},{"subjectId":190,"subjectName":"腰带","status":0,"createTime":"2019-03-21 17:00:45","updateTime":"2019-04-20 16:49:30","parentId":38,"level":2,"image":"","sort":1,"childSubjectList":[{"subjectId":191,"subjectName":"真皮腰带","status":0,"createTime":"2019-03-21 17:01:03","updateTime":"2019-04-30 09:31:20","parentId":190,"level":3,"image":"","icon":"/upload/mavendemo/course/20190430/1556587879377039022.jpg","sort":0,"childSubjectList":[]}]},{"subjectId":270,"subjectName":"袜子","status":0,"createTime":"2019-03-22 14:26:29","updateTime":"2019-04-20 16:51:24","parentId":38,"level":2,"image":"","sort":0,"childSubjectList":[{"subjectId":271,"subjectName":"休闲棉袜","status":0,"createTime":"2019-03-22 14:29:50","updateTime":"2019-04-30 09:32:21","parentId":270,"level":3,"image":"","icon":"/upload/mavendemo/course/20190430/1556587939981955155.jpg","sort":0,"childSubjectList":[]},{"subjectId":272,"subjectName":"女袜","status":0,"createTime":"2019-03-22 14:30:21","updateTime":"2019-04-30 09:32:37","parentId":270,"level":3,"image":"","icon":"/upload/mavendemo/course/20190430/1556587956692639303.jpg","sort":0,"childSubjectList":[]}]}]},{"subjectId":16,"subjectName":"食品酒水","status":0,"createTime":"2019-03-07 09:53:00","updateTime":"2019-03-28 11:01:09","parentId":0,"level":1,"image":"","sort":7,"childSubjectList":[{"subjectId":36,"subjectName":"食品","status":0,"createTime":"2019-03-15 10:21:36","updateTime":"2019-03-15 10:21:36","parentId":16,"level":2,"image":"","sort":0,"childSubjectList":[{"subjectId":340,"subjectName":"海鲜","status":0,"createTime":"2019-03-25 10:55:21","updateTime":"2019-04-29 12:53:53","parentId":36,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556513629774773235.png","sort":0,"childSubjectList":[]},{"subjectId":129,"subjectName":"休闲食品","status":0,"createTime":"2019-03-21 15:00:07","updateTime":"2019-04-29 12:54:26","parentId":36,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556513664546081628.png","sort":0,"childSubjectList":[]},{"subjectId":428,"subjectName":"食品礼包","status":0,"createTime":"2019-03-29 14:52:43","updateTime":"2019-04-29 12:55:02","parentId":36,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556513700858664606.png","sort":0,"childSubjectList":[]}]},{"subjectId":37,"subjectName":"酒水饮料","status":0,"createTime":"2019-03-15 10:21:43","updateTime":"2019-03-17 11:10:55","parentId":16,"level":2,"image":"","sort":0,"childSubjectList":[{"subjectId":132,"subjectName":"白酒","status":0,"createTime":"2019-03-21 15:02:40","updateTime":"2019-04-29 12:55:52","parentId":37,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556513747583339971.png","sort":0,"childSubjectList":[]},{"subjectId":445,"subjectName":"啤酒饮料","status":0,"createTime":"2019-04-18 13:42:12","updateTime":"2019-04-29 12:56:38","parentId":37,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556513791048715587.png","sort":0,"childSubjectList":[]}]},{"subjectId":45,"subjectName":"米面粮油","status":0,"createTime":"2019-03-15 10:24:21","updateTime":"2019-03-15 10:24:21","parentId":16,"level":2,"image":"","sort":0,"childSubjectList":[{"subjectId":136,"subjectName":"大米","status":0,"createTime":"2019-03-21 15:06:40","updateTime":"2019-04-29 12:57:05","parentId":45,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556513821539383561.png","sort":0,"childSubjectList":[]},{"subjectId":137,"subjectName":"食用油","status":0,"createTime":"2019-03-21 15:06:54","updateTime":"2019-04-29 12:57:32","parentId":45,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556513848866194901.png","sort":0,"childSubjectList":[]},{"subjectId":139,"subjectName":"五谷杂粮","status":0,"createTime":"2019-03-21 15:07:25","updateTime":"2019-04-29 12:58:05","parentId":45,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556513880635329279.png","sort":0,"childSubjectList":[]},{"subjectId":140,"subjectName":"其他","status":0,"createTime":"2019-03-21 15:07:53","updateTime":"2019-05-06 10:56:41","parentId":45,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556513905757516970.png","sort":0,"childSubjectList":[]}]},{"subjectId":68,"subjectName":"茶","status":0,"createTime":"2019-03-16 15:37:44","updateTime":"2019-03-16 15:37:44","parentId":16,"level":2,"image":"","sort":0,"childSubjectList":[{"subjectId":141,"subjectName":"茗茶","status":0,"createTime":"2019-03-21 15:08:43","updateTime":"2019-04-29 12:58:53","parentId":68,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556513930576881435.png","sort":0,"childSubjectList":[]}]}]},{"subjectId":12,"subjectName":"家居家厨","status":0,"createTime":"2019-03-04 10:24:30","updateTime":"2019-03-28 11:03:00","parentId":0,"level":1,"image":"/upload/mavendemo/course/20190305/1551748168654339875.jpeg","sort":6,"childSubjectList":[{"subjectId":46,"subjectName":"家居用品","status":0,"createTime":"2019-03-15 10:24:59","updateTime":"2019-03-15 10:26:26","parentId":12,"level":2,"image":"","sort":0,"childSubjectList":[{"subjectId":121,"subjectName":"床上用品","status":0,"createTime":"2019-03-21 14:47:20","updateTime":"2019-04-29 12:59:38","parentId":46,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556513970604376685.png","sort":0,"childSubjectList":[]},{"subjectId":122,"subjectName":"家纺软饰","status":0,"createTime":"2019-03-21 14:48:41","updateTime":"2019-04-29 13:01:50","parentId":46,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556514108492989908.png","sort":0,"childSubjectList":[]},{"subjectId":124,"subjectName":"咖啡壶","status":0,"createTime":"2019-03-21 14:51:19","updateTime":"2019-05-06 10:57:20","parentId":46,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556514137114855347.png","sort":0,"childSubjectList":[]},{"subjectId":128,"subjectName":"杯具/茶具","status":0,"createTime":"2019-03-21 14:54:47","updateTime":"2019-04-29 13:02:43","parentId":46,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556514161027750844.png","sort":0,"childSubjectList":[]}]},{"subjectId":47,"subjectName":"厨房用品","status":0,"createTime":"2019-03-15 10:26:13","updateTime":"2019-03-15 10:26:13","parentId":12,"level":2,"image":"","sort":0,"childSubjectList":[{"subjectId":125,"subjectName":"刀具板勺","status":0,"createTime":"2019-03-21 14:51:52","updateTime":"2019-05-06 10:57:43","parentId":47,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556514186992976883.png","sort":0,"childSubjectList":[]},{"subjectId":126,"subjectName":"厨卫清洁","status":0,"createTime":"2019-03-21 14:52:23","updateTime":"2019-04-30 09:33:16","parentId":47,"level":3,"image":"","icon":"/upload/mavendemo/course/20190430/1556587995061232764.jpg","sort":0,"childSubjectList":[]},{"subjectId":127,"subjectName":"锅具/套件","status":0,"createTime":"2019-03-21 14:53:37","updateTime":"2019-04-29 13:04:18","parentId":47,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556514253282393071.png","sort":0,"childSubjectList":[]},{"subjectId":249,"subjectName":"餐具","status":0,"createTime":"2019-03-21 17:45:49","updateTime":"2019-04-29 13:04:50","parentId":47,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556514289129254261.png","sort":0,"childSubjectList":[]},{"subjectId":250,"subjectName":"厨房配件","status":0,"createTime":"2019-03-21 17:46:04","updateTime":"2019-04-29 13:05:12","parentId":47,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556514310739426465.png","sort":0,"childSubjectList":[]}]},{"subjectId":303,"subjectName":"日化用品","status":0,"createTime":"2019-03-22 17:34:43","updateTime":"2019-03-22 17:34:43","parentId":12,"level":2,"image":"","sort":0,"childSubjectList":[{"subjectId":305,"subjectName":"洗衣液","status":0,"createTime":"2019-03-22 17:35:53","updateTime":"2019-05-06 10:58:05","parentId":303,"level":3,"image":"","icon":"/upload/mavendemo/course/20190430/1556588021826989160.jpg","sort":0,"childSubjectList":[]},{"subjectId":306,"subjectName":"纸巾","status":0,"createTime":"2019-03-22 17:44:44","updateTime":"2019-05-06 10:58:18","parentId":303,"level":3,"image":"","icon":"/upload/mavendemo/course/20190430/1556588046267227969.jpg","sort":0,"childSubjectList":[]},{"subjectId":354,"subjectName":"生活用品","status":0,"createTime":"2019-03-26 09:47:58","updateTime":"2019-04-29 13:07:06","parentId":303,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556514424977957056.png","sort":0,"childSubjectList":[]}]},{"subjectId":334,"subjectName":"五金工具/耗材","status":0,"createTime":"2019-03-25 10:35:04","updateTime":"2019-03-25 10:35:04","parentId":12,"level":2,"image":"","sort":0,"childSubjectList":[{"subjectId":337,"subjectName":"工具套装","status":0,"createTime":"2019-03-25 10:36:13","updateTime":"2019-04-29 13:07:31","parentId":334,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556514450106103059.png","sort":0,"childSubjectList":[]}]}]},{"subjectId":15,"subjectName":"健康保健","status":0,"createTime":"2019-03-05 15:53:43","updateTime":"2019-03-28 11:01:16","parentId":0,"level":1,"image":"","sort":4,"childSubjectList":[{"subjectId":62,"subjectName":"膳食补品","status":0,"createTime":"2019-03-15 11:14:11","updateTime":"2019-03-15 11:14:50","parentId":15,"level":2,"image":"","sort":0,"childSubjectList":[{"subjectId":144,"subjectName":"膳食纤维","status":0,"createTime":"2019-03-21 15:10:39","updateTime":"2019-05-06 10:59:28","parentId":62,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556514474233325824.png","sort":0,"childSubjectList":[]}]},{"subjectId":63,"subjectName":"保健食品","status":0,"createTime":"2019-03-15 11:14:25","updateTime":"2019-03-15 11:14:25","parentId":15,"level":2,"image":"","sort":0,"childSubjectList":[{"subjectId":152,"subjectName":"养生产品","status":0,"createTime":"2019-03-21 16:26:38","updateTime":"2019-04-29 13:08:20","parentId":63,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556514499445752272.png","sort":0,"childSubjectList":[]}]},{"subjectId":72,"subjectName":"健康用品","status":0,"createTime":"2019-03-17 10:57:29","updateTime":"2019-03-17 10:57:29","parentId":15,"level":2,"image":"","sort":0,"childSubjectList":[{"subjectId":153,"subjectName":"健康监测","status":0,"createTime":"2019-03-21 16:27:43","updateTime":"2019-04-29 13:08:44","parentId":72,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556514522821827674.png","sort":0,"childSubjectList":[]},{"subjectId":154,"subjectName":"健康防护","status":0,"createTime":"2019-03-21 16:29:02","updateTime":"2019-05-06 10:59:13","parentId":72,"level":3,"image":"","icon":"/upload/mavendemo/course/20190430/1556588088521575391.jpg","sort":0,"childSubjectList":[]},{"subjectId":155,"subjectName":"足浴按摩","status":0,"createTime":"2019-03-21 16:29:55","updateTime":"2019-05-06 11:00:34","parentId":72,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556514574607295866.png","sort":0,"childSubjectList":[]}]}]},{"subjectId":64,"subjectName":"汽车用品","status":0,"createTime":"2019-03-15 14:36:49","updateTime":"2019-03-28 11:03:12","parentId":0,"level":1,"image":"","sort":3,"childSubjectList":[{"subjectId":66,"subjectName":"汽车装饰","status":0,"createTime":"2019-03-15 14:38:33","updateTime":"2019-03-15 14:38:33","parentId":64,"level":2,"image":"","sort":0,"childSubjectList":[{"subjectId":158,"subjectName":"方向盘套","status":0,"createTime":"2019-03-21 16:37:16","updateTime":"2019-04-30 09:35:12","parentId":66,"level":3,"image":"","icon":"/upload/mavendemo/course/20190430/1556588110592262724.jpg","sort":0,"childSubjectList":[]},{"subjectId":159,"subjectName":"汽车坐垫","status":0,"createTime":"2019-03-21 16:37:34","updateTime":"2019-04-30 09:35:32","parentId":66,"level":3,"image":"","icon":"/upload/mavendemo/course/20190430/1556588131546393118.jpg","sort":0,"childSubjectList":[]}]},{"subjectId":67,"subjectName":"车载电器","status":0,"createTime":"2019-03-15 14:38:41","updateTime":"2019-03-15 14:38:41","parentId":64,"level":2,"image":"","sort":0,"childSubjectList":[{"subjectId":161,"subjectName":"净化器","status":0,"createTime":"2019-03-21 16:41:31","updateTime":"2019-05-06 10:58:34","parentId":67,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556514639528713446.png","sort":0,"childSubjectList":[]},{"subjectId":163,"subjectName":"智能设备","status":0,"createTime":"2019-03-21 16:42:20","updateTime":"2019-05-06 10:59:39","parentId":67,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556514659056569960.png","sort":0,"childSubjectList":[]}]},{"subjectId":492,"subjectName":"汽车保养","status":0,"createTime":"2019-05-10 16:20:42","updateTime":"2019-05-10 16:20:42","parentId":64,"level":2,"image":"","icon":"","sort":0,"childSubjectList":[{"subjectId":493,"subjectName":"汽车清洁","status":0,"createTime":"2019-05-10 16:21:23","updateTime":"2019-05-10 16:21:23","parentId":492,"level":3,"image":"","icon":"","sort":0,"childSubjectList":[]},{"subjectId":494,"subjectName":"汽车保养","status":0,"createTime":"2019-05-10 16:21:49","updateTime":"2019-05-10 16:21:49","parentId":492,"level":3,"image":"","icon":"","sort":0,"childSubjectList":[]}]}]},{"subjectId":10,"subjectName":"母婴/儿童","status":0,"createTime":"2019-03-04 10:24:05","updateTime":"2019-03-28 11:00:51","parentId":0,"level":1,"image":"/upload/mavendemo/course/20190305/1551751192544674802.jpeg","sort":2,"childSubjectList":[{"subjectId":54,"subjectName":"母婴用品","status":0,"createTime":"2019-03-15 10:29:53","updateTime":"2019-03-26 09:24:31","parentId":10,"level":2,"image":"","sort":0,"childSubjectList":[{"subjectId":149,"subjectName":"婴儿用品","status":0,"createTime":"2019-03-21 16:24:34","updateTime":"2019-05-06 11:01:11","parentId":54,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556514679877715781.png","sort":0,"childSubjectList":[]}]},{"subjectId":146,"subjectName":"儿童用品","status":0,"createTime":"2019-03-21 16:20:33","updateTime":"2019-03-26 10:36:52","parentId":10,"level":2,"image":"","sort":0,"childSubjectList":[{"subjectId":148,"subjectName":"玩具用品","status":0,"createTime":"2019-03-21 16:22:26","updateTime":"2019-05-06 11:00:52","parentId":146,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556514698892626635.png","sort":0,"childSubjectList":[]}]}]},{"subjectId":59,"subjectName":"户外健身","status":0,"createTime":"2019-03-15 11:11:54","updateTime":"2019-03-28 11:03:07","parentId":0,"level":1,"image":"","sort":2,"childSubjectList":[{"subjectId":60,"subjectName":"户外用品","status":0,"createTime":"2019-03-15 11:12:36","updateTime":"2019-03-15 11:12:36","parentId":59,"level":2,"image":"","sort":0,"childSubjectList":[{"subjectId":403,"subjectName":"户外照明","status":0,"createTime":"2019-03-27 15:14:47","updateTime":"2019-04-29 13:12:37","parentId":60,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556514754939547369.png","sort":0,"childSubjectList":[]}]},{"subjectId":61,"subjectName":"健身器材","status":0,"createTime":"2019-03-15 11:12:45","updateTime":"2019-03-15 11:12:45","parentId":59,"level":2,"image":"","sort":0,"childSubjectList":[{"subjectId":156,"subjectName":"健身器材","status":0,"createTime":"2019-03-21 16:34:28","updateTime":"2019-04-29 13:13:06","parentId":61,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556514783515570941.png","sort":0,"childSubjectList":[]}]}]},{"subjectId":427,"subjectName":"旅游","status":0,"createTime":"2019-03-28 11:14:34","updateTime":"2019-03-28 11:14:34","parentId":0,"level":1,"image":"","sort":2,"childSubjectList":[]},{"subjectId":463,"subjectName":"珠宝首饰","status":0,"createTime":"2019-04-19 10:33:20","updateTime":"2019-04-19 10:33:20","parentId":0,"level":1,"image":"","sort":0,"childSubjectList":[{"subjectId":464,"subjectName":"珠宝首饰","status":0,"createTime":"2019-04-19 10:40:43","updateTime":"2019-04-19 11:13:46","parentId":463,"level":2,"image":"","sort":17,"childSubjectList":[{"subjectId":465,"subjectName":"足金","status":0,"createTime":"2019-04-19 10:44:34","updateTime":"2019-04-29 13:13:47","parentId":464,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556514825003027233.png","sort":0,"childSubjectList":[]},{"subjectId":466,"subjectName":"K金","status":0,"createTime":"2019-04-19 10:44:49","updateTime":"2019-04-29 13:14:27","parentId":464,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556514865045274007.png","sort":0,"childSubjectList":[]},{"subjectId":467,"subjectName":"时尚饰品","status":0,"createTime":"2019-04-19 10:45:23","updateTime":"2019-04-29 13:14:44","parentId":464,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556514883597600862.png","sort":0,"childSubjectList":[]},{"subjectId":468,"subjectName":"木串把件","status":0,"createTime":"2019-04-19 10:47:27","updateTime":"2019-04-29 13:15:04","parentId":464,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556514903124538970.png","sort":0,"childSubjectList":[]},{"subjectId":476,"subjectName":"翡翠玉石","status":0,"createTime":"2019-04-19 11:14:54","updateTime":"2019-04-29 13:15:26","parentId":464,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556514924980097615.png","sort":0,"childSubjectList":[]},{"subjectId":477,"subjectName":"银饰","status":0,"createTime":"2019-04-19 13:12:27","updateTime":"2019-04-29 13:15:42","parentId":464,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556514941528867976.png","sort":0,"childSubjectList":[]}]}]}]
     */

    private String message;
    private boolean success;
    private List<EntityBean> entity;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<EntityBean> getEntity() {
        return entity;
    }

    public void setEntity(List<EntityBean> entity) {
        this.entity = entity;
    }

    public static class EntityBean {
        @Override
        public String toString() {
            return "EntityBean{" +
                    "subjectId=" + subjectId +
                    ", subjectName='" + subjectName + '\'' +
                    ", status=" + status +
                    ", createTime='" + createTime + '\'' +
                    ", updateTime='" + updateTime + '\'' +
                    ", parentId=" + parentId +
                    ", level=" + level +
                    ", image='" + image + '\'' +
                    ", sort=" + sort +
                    ", icon='" + icon + '\'' +
                    ", childSubjectList=" + childSubjectList +
                    '}';
        }

        /**
         * subjectId : 449
         * subjectName : 手机数码
         * status : 0
         * createTime : 2019-04-18 17:42:03
         * updateTime : 2019-04-18 17:42:03
         * parentId : 0
         * level : 1
         * image :
         * sort : 15
         * childSubjectList : [{"subjectId":450,"subjectName":"手机","status":0,"createTime":"2019-04-18 17:42:26","updateTime":"2019-04-18 17:42:26","parentId":449,"level":2,"image":"","sort":0,"childSubjectList":[{"subjectId":456,"subjectName":"手机","status":0,"createTime":"2019-04-18 17:44:36","updateTime":"2019-04-29 11:08:03","parentId":450,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556507281432560448.jpg","sort":0,"childSubjectList":[]},{"subjectId":457,"subjectName":"手机配件","status":0,"createTime":"2019-04-18 17:44:55","updateTime":"2019-04-29 11:08:17","parentId":450,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556507296387922382.jpg","sort":0,"childSubjectList":[]}]},{"subjectId":452,"subjectName":"数码产品","status":0,"createTime":"2019-04-18 17:42:42","updateTime":"2019-04-18 17:45:34","parentId":449,"level":2,"image":"","sort":0,"childSubjectList":[{"subjectId":458,"subjectName":"耳机","status":0,"createTime":"2019-04-18 17:45:06","updateTime":"2019-04-30 09:23:34","parentId":452,"level":3,"image":"","icon":"/upload/mavendemo/course/20190430/1556587411373285427.jpg","sort":0,"childSubjectList":[]},{"subjectId":459,"subjectName":"音响","status":0,"createTime":"2019-04-18 17:45:45","updateTime":"2019-04-30 09:23:47","parentId":452,"level":3,"image":"","icon":"/upload/mavendemo/course/20190430/1556587424317382120.jpg","sort":0,"childSubjectList":[]},{"subjectId":460,"subjectName":"无人机","status":0,"createTime":"2019-04-18 17:45:54","updateTime":"2019-04-30 09:24:03","parentId":452,"level":3,"image":"","icon":"/upload/mavendemo/course/20190430/1556587440077306294.jpg","sort":0,"childSubjectList":[]},{"subjectId":461,"subjectName":"摄影自拍","status":0,"createTime":"2019-04-18 17:46:09","updateTime":"2019-04-30 09:24:19","parentId":452,"level":3,"image":"","icon":"/upload/mavendemo/course/20190430/1556587457687791315.jpg","sort":0,"childSubjectList":[]},{"subjectId":462,"subjectName":"智能手表","status":0,"createTime":"2019-04-18 18:00:00","updateTime":"2019-04-30 09:24:35","parentId":452,"level":3,"image":"","icon":"/upload/mavendemo/course/20190430/1556587473747989433.jpg","sort":0,"childSubjectList":[]}]}]
         * icon :
         */

        private int subjectId;
        private String subjectName;
        private int status;
        private String createTime;
        private String updateTime;
        private int parentId;
        private int level;
        private String image;
        private int sort;
        private boolean check;

        private String icon;
        private List<ChildSubjectListBeanX> childSubjectList;

        public boolean isCheck() {
            return check;
        }

        public void setCheck(boolean check) {
            this.check = check;
        }

        public int getSubjectId() {
            return subjectId;
        }

        public void setSubjectId(int subjectId) {
            this.subjectId = subjectId;
        }

        public String getSubjectName() {
            return subjectName;
        }

        public void setSubjectName(String subjectName) {
            this.subjectName = subjectName;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public List<ChildSubjectListBeanX> getChildSubjectList() {
            return childSubjectList;
        }

        public void setChildSubjectList(List<ChildSubjectListBeanX> childSubjectList) {
            this.childSubjectList = childSubjectList;
        }

        public static class ChildSubjectListBeanX {
            @Override
            public String toString() {
                return "ChildSubjectListBeanX{" +
                        "subjectId=" + subjectId +
                        ", subjectName='" + subjectName + '\'' +
                        ", status=" + status +
                        ", createTime='" + createTime + '\'' +
                        ", updateTime='" + updateTime + '\'' +
                        ", parentId=" + parentId +
                        ", level=" + level +
                        ", image='" + image + '\'' +
                        ", sort=" + sort +
                        ", childSubjectList=" + childSubjectList +
                        '}';
            }

            /**
             * subjectId : 450
             * subjectName : 手机
             * status : 0
             * createTime : 2019-04-18 17:42:26
             * updateTime : 2019-04-18 17:42:26
             * parentId : 449
             * level : 2
             * image :
             * sort : 0
             * childSubjectList : [{"subjectId":456,"subjectName":"手机","status":0,"createTime":"2019-04-18 17:44:36","updateTime":"2019-04-29 11:08:03","parentId":450,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556507281432560448.jpg","sort":0,"childSubjectList":[]},{"subjectId":457,"subjectName":"手机配件","status":0,"createTime":"2019-04-18 17:44:55","updateTime":"2019-04-29 11:08:17","parentId":450,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556507296387922382.jpg","sort":0,"childSubjectList":[]}]
             */

            private int subjectId;
            private String subjectName;
            private int status;
            private String createTime;
            private String updateTime;
            private int parentId;
            private int level;
            private String image;
            private int sort;
            private List<ChildSubjectListBean> childSubjectList;

            public int getSubjectId() {
                return subjectId;
            }

            public void setSubjectId(int subjectId) {
                this.subjectId = subjectId;
            }

            public String getSubjectName() {
                return subjectName;
            }

            public void setSubjectName(String subjectName) {
                this.subjectName = subjectName;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public List<ChildSubjectListBean> getChildSubjectList() {
                return childSubjectList;
            }

            public void setChildSubjectList(List<ChildSubjectListBean> childSubjectList) {
                this.childSubjectList = childSubjectList;
            }

            public static class ChildSubjectListBean {
                @Override
                public String toString() {
                    return "ChildSubjectListBean{" +
                            "subjectId=" + subjectId +
                            ", subjectName='" + subjectName + '\'' +
                            ", status=" + status +
                            ", createTime='" + createTime + '\'' +
                            ", updateTime='" + updateTime + '\'' +
                            ", parentId=" + parentId +
                            ", level=" + level +
                            ", image='" + image + '\'' +
                            ", icon='" + icon + '\'' +
                            ", sort=" + sort +
//                            ", childSubjectList=" + childSubjectList +
                            '}';
                }

                /**
                 * subjectId : 456
                 * subjectName : 手机
                 * status : 0
                 * createTime : 2019-04-18 17:44:36
                 * updateTime : 2019-04-29 11:08:03
                 * parentId : 450
                 * level : 3
                 * image :
                 * icon : /upload/mavendemo/course/20190429/1556507281432560448.jpg
                 * sort : 0
                 * childSubjectList : []
                 */

                private int subjectId;
                private String subjectName;
                private int status;
                private String createTime;
                private String updateTime;
                private int parentId;
                private int level;
                private String image;
                private String icon;
                private int sort;
//                private List<?> childSubjectList;

                public int getSubjectId() {
                    return subjectId;
                }

                public void setSubjectId(int subjectId) {
                    this.subjectId = subjectId;
                }

                public String getSubjectName() {
                    return subjectName;
                }

                public void setSubjectName(String subjectName) {
                    this.subjectName = subjectName;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public String getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(String createTime) {
                    this.createTime = createTime;
                }

                public String getUpdateTime() {
                    return updateTime;
                }

                public void setUpdateTime(String updateTime) {
                    this.updateTime = updateTime;
                }

                public int getParentId() {
                    return parentId;
                }

                public void setParentId(int parentId) {
                    this.parentId = parentId;
                }

                public int getLevel() {
                    return level;
                }

                public void setLevel(int level) {
                    this.level = level;
                }

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }

                public int getSort() {
                    return sort;
                }

                public void setSort(int sort) {
                    this.sort = sort;
                }

//                public List<?> getChildSubjectList() {
//                    return childSubjectList;
//                }
//
//                public void setChildSubjectList(List<?> childSubjectList) {
//                    this.childSubjectList = childSubjectList;
//                }
            }
        }
    }
}
