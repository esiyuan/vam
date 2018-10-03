<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html class='no-js' lang='en'>
<head>
    <meta charset='utf-8'>
    <meta content='IE=edge,chrome=1' http-equiv='X-UA-Compatible'>
    <title>任务类型</title>
    <link href="/assets/stylesheets/application-a07755f5.css" rel="stylesheet" type="text/css"/>
    <link href="/assets/images/favicon.ico" rel="icon" type="image/ico"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body class='main page'>

<div>
    <div id='' style="width: 80%; margin-left: 10%">
        <div class='panel panel-default'>
            <div class='panel-body'>
                <legend>任务类型设置</legend>
                <form class='form-horizontal' role="form" action="typeSave" method="post">
                    <fieldset>
                        <div class="form-group">
                            <label class="col-md-2 control-label">任务类型</label>
                            <div class="col-md-10">
                                <input type="text" class="form-control" name="bizType">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">最大执行次数</label>
                            <div class="col-md-10">
                                <input type="text" class="form-control" name="maxRunTimes">
                            </div>
                        </div>
                        <div class='form-group'>
                            <label class="col-md-2 control-label">执行器类型</label>
                            <div class="col-md-10">
                                <select class='form-control' name="execType">
                                    <option value="springBeanExecutor">SpringBean同步执行器</option>
                                    <option>异步回调执行器</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-2 control-label">服务名称</label>
                            <div class="col-md-10">
                                <input type="text" class="form-control" name="execService"
                                       placeholder="SpringBean方式为Bean的名字"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">方法名称</label>
                            <div class="col-md-10">
                                <input type="text" class="form-control" name="execMethod" placeholder="SpringBean方式必要"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">重复执行间隔时间</label>
                            <div class="col-md-10">
                                <input type="text" class="form-control" name="runIntervals"
                                       placeholder="未列举出来的取最后的，例如:1,3,5,9"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">首次执行延迟时间</label>
                            <div class="col-md-10">
                                <input type="text" class="form-control" name="delayedRunMinutes"
                                       placeholder="从当前时间开始延迟多久执行，单位：分钟"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">单次执行任务数</label>
                            <div class="col-md-10">
                                <input type="text" class="form-control" name="perPageCount"
                                       placeholder="例如，对SpringBean来说，方法执行传递的任务数量"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">此类任务的最大并行线程</label>
                            <div class="col-md-10">
                                <input type="text" class="form-control" name="perThreadCount"
                                       placeholder="只对异步执行方式有效，SpringBean方式无效"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">超时时间</label>
                            <div class="col-md-10">
                                <input type="text" class="form-control" name="timeoutMinutes"
                                       placeholder="任务执行后多久未返回后标记失败，等待下次重试"/>
                            </div>
                        </div>

                        <div class='form-group'>
                            <label class="col-md-2 control-label">失败处理方式</label>
                            <div class="col-md-10">
                                <select class='form-control' name="execType">
                                    <option value="springBeanExecutor">转移到异常任务中，等待人工补偿</option>
                                    <option value="springBeanExecutor">重新转移到新任务类型，等待新的任务触发</option>
                                </select>
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="col-md-2 control-label">新的或者异常的任务类型</label>
                            <div class="col-md-10">
                                <input type="text" class="form-control" name="exceptionProc"/>
                            </div>
                        </div>

                    </fieldset>

                    <div class='form-actions' style="padding-left: 20%">
                        <button class='btn btn-default' type='submit'>提交</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
