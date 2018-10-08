<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html class='no-js' lang='en'>
<head>

    <meta charset='utf-8'>
    <meta content='IE=edge,chrome=1' http-equiv='X-UA-Compatible'>
    <title>任务详情管理</title>
    <link href="/assets/stylesheets/application-a07755f5.css" rel="stylesheet" type="text/css"/>
    <link href="/assets/images/favicon.ico" rel="icon" type="image/ico"/>
    <script src="/assets/javascripts/jquery.min.js.js"></script>
    <script src="/assets/laydate/laydate.js"></script>
    <script src="/assets/javascripts/taskInfoList.js"></script>
</head>
<body class=''>
<div id=''>
    <div class='panel panel-default grid'>
        <div class='panel-body'>
            <form id="typeListForm" method="post" class="form-inline" role="form">
                <input id="typeListPageStart" hidden="hidden" name="start" type='text'
                       value="${page["start"]}"/>

                <div class="row">
                    <div class="col-md-9" style="padding-left: 10%">
                        <fieldset>
                            <div class="row">
                                <div class='form-group col-md-6'>
                                    <label class="control-label ">任务类型:</label>
                                    <input type="text" class="form-control" name="execMethod"
                                           value="${taskType[" execMethod"]}"/>
                                </div>
                                <div class='form-group col-md-6'>
                                    <label class="control-label">业务主键:</label>
                                    <input type="text" class="form-control" name="execMethod"
                                           value="${taskType[" execMethod"]}"/>

                                </div>
                            </div>

                            <div class="row">
                                <div class='form-group col-md-6'>
                                    <label class="control-label">结束时间:</label>
                                    <input id="nextRunTimeStart" type="text" class="form-control" name="execMethod"
                                           value=""/>
                                </div>
                                <div class='form-group col-md-6'>
                                    <label class="control-label">结束时间:</label>
                                    <input id="nextRunTimeEnd" type="text" class="form-control" name="execMethod"
                                           value="" placeholder="SpringBean方式必要"/>
                                </div>
                            </div>

                        </fieldset>
                    </div>
                    <div class="col-md-3" style="border: 10px solid  #0f705d;">
                        TWO
                    </div>
                </div>


            </form>
        </div>
        <table class='table'>
            <c:if test="${empty typeList}">
                <tr>
                    <td><span>没有数据！</span></td>
                </tr>
            </c:if>

            <thead>
            <tr>
                <th>序号</th>
                <th>任务类型</th>
                <th>业务主键</th>
                <th>运行次数</th>
                <th>下次执行时间</th>
                <th>超时时间</th>
                <th>创建时间</th>
                <th>状态</th>
                <th class='actions'>
                    操作
                </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${taskList}" varStatus="status" var="each">
                <tr class=''>
                    <td>${status["index"] + 1}</td>
                    <td>${each["bizType"]}</td>
                    <td>${each["bizKey"]}</td>
                    <td>${each["runCount"]}</td>
                    <td>${each["nextRunTime"]}</td>
                    <td>${each["timeoutTime"]}</td>
                    <td>${each["createTime"]}</td>
                    <td>
                        <c:choose>
                            <c:when test="${each['status'] == 0}">
                                <i class='glyphicon'>关闭</i>
                            </c:when>
                            <c:when test="${each['status'] == 1}">
                                <i class='glyphicon'>开启</i>
                            </c:when>
                        </c:choose>
                    </td>

                    <td class='action'>
                        <a class='btn btn-info' data-toggle='tooltip' href='update?id=${each["id"]}' title='修改'>
                            <i class='glyphicon glyphicon-edit'></i>
                        </a>

                        <button class='btn btn-danger' type='button' onclick="del(${each["id"]})"
                                title="删除">
                            <i class='glyphicon glyphicon-trash'></i>
                        </button>
                        <c:if test="${each['status'] == 0}">
                            <button class='btn btn-warning' type='button' onclick="statusConvert(${each["id"]}, 1)"
                                    title="开启">
                                <i class='glyphicon glyphicon-play'></i>
                            </button>
                        </c:if>
                        <c:if test="${each['status'] == 1}">
                            <button class='btn btn-warning' type='button' onclick="statusConvert(${each["id"]}, 0)"
                                    title="关闭">
                                <i class='glyphicon glyphicon-stop'></i>
                            </button>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>

        <div style="display: none;">
            <form id="statusConvertForm" action="convertStatus" method="post">
                <input type="text" id="statusConvertForm_status" name="status">
                <input type="text" id="statusConvertForm_id" name="id">
                <input type="text" id="statusConvertForm_bizType" name="bizType">
            </form>
        </div>

        <div class='panel-footer' style="padding-left: 70%;">
            <div class='pull-left'>
                展示${page.indexStart} 到 ${page.indexEnd} 条 / 总共 ${page.totalCount} 条
            </div>
            <ul class='pagination pagination-sm '>
                <li>
                    <a href='#' onclick="taskSearch(${page["start"] - 1})">上一页</a>
                </li>

                <li>
                    <a href='#' onclick="taskSearch(${page["start"] + 1})">下一页</a>
                </li>
            </ul>

        </div>
    </div>



</div>


</body>
</html>