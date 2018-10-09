<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html class='no-js' lang='en'>
<head>

    <meta charset='utf-8'>
    <meta content='IE=edge,chrome=1' http-equiv='X-UA-Compatible'>
    <title>任务详情管理</title>
    <link href="/assets/stylesheets/application-a07755f5.css" rel="stylesheet" type="text/css"/>
    <link href="/assets/page/pagination.css" rel="stylesheet" type="text/css"/>
    <link href="/assets/images/favicon.ico" rel="icon" type="image/ico"/>
    <script src="/assets/javascripts/jquery.min.js.js"></script>
    <script src="/assets/laydate/laydate.js"></script>
    <script src="/assets/page/pagination.js"></script>
    <script src="/assets/page/page.js"></script>
    <script src="/assets/javascripts/taskInfoList.js"></script>

</head>
<body class=''>
<div id=''>
    <div class='panel panel-default grid'>
        <div class='panel-body'>
            <form id="listForm" method="post" action="search.htm" class="form-inline" role="form">
                <%@include file="./common/cur_index.jsp" %>
                <div class="row">
                    <fieldset>
                        <div style="padding-left: 10%">

                            <div class="row">
                                <div class='form-group col-md-4'>
                                    <label class="control-label ">任务类型:</label>
                                    <input type="text" class="form-control" name="bizType"
                                           value="${param.bizType}"/>
                                </div>
                                <div class='form-group col-md-4'>
                                    <label class="control-label">业务主键:</label>
                                    <input type="text" class="form-control" name="bizKey"
                                           value="${param.bizKey}"/>
                                </div>
                                <div class='form-group col-md-4'>
                                    <label class="control-label">任务状态:</label>
                                    <input hidden="hidden" id="selectStatus" name="status"
                                           value="${param["status"]}"/>
                                    <select class='form-control' id="statusSelect"
                                            onchange="statusSelect()">
                                        <option value="0">待执行</option>
                                        <option value="1">执行中</option>
                                    </select>
                                </div>
                            </div>

                            <div class="row">
                                <div class='form-group col-md-4'>
                                    <label class="control-label">结束时间:</label>
                                    <input id="nextRunTimeStart" type="text" class="form-control"
                                           name="nextRunTimeStart"
                                           value="${param.nextRunTimeStart}}"/>
                                </div>
                                <div class='form-group col-md-4'>
                                    <label class="control-label">结束时间:</label>
                                    <input id="nextRunTimeEnd" type="text" class="form-control" name="nextRunTimeEnd"
                                           value="${param.nextRunTimeEnd}"/>
                                </div>
                                <div class='form-group col-md-4'>
                                    <button class='btn' type='button' value="查询" onclick="taskSearch()" title="查询">
                                        <i class='glyphicon glyphicon-search'></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                </div>
            </form>
        </div>
        <table class='table'>
            <c:if test="${empty taskList}">
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
                        <button class='btn btn-danger' type='button' onclick="del(${each["id"]})"
                                title="删除">
                            <i class='glyphicon glyphicon-trash'></i>
                        </button>
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
        <%@include file="./common/page.jsp" %>
    </div>
</div>
</body>
</html>
