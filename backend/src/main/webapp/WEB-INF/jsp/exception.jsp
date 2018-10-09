<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html class='no-js' lang='en'>
<head>

    <meta charset='utf-8'>
    <meta content='IE=edge,chrome=1' http-equiv='X-UA-Compatible'>
    <title>异常管理</title>
    <link href="/assets/stylesheets/application-a07755f5.css" rel="stylesheet" type="text/css"/>
    <link href="/assets/page/pagination.css" rel="stylesheet" type="text/css"/>
    <link href="/assets/images/favicon.ico" rel="icon" type="image/ico"/>
    <script src="/assets/javascripts/jquery.min.js.js"></script>
    <script src="/assets/laydate/laydate.js"></script>
    <script src="/assets/page/pagination.js"></script>
    <script src="/assets/page/page.js"></script>
    <script src="/assets/javascripts/exception.js"></script>

</head>
<body class=''>
<div id=''>
    <div class='panel panel-default grid'>
        <div class='panel-body'>
            <form id="listForm" method="post" action="search.htm" class="form-inline" role="form">
                <%@include file="./common/cur_index.jsp" %>
                <div class="row"  style="padding-left: 10%;padding-right: 10%" >
                    <fieldset>
                        <div class="form-group col-md-10">

                            <div class="row">
                                <div class='form-group col-md-6'>
                                    <label class="control-label ">任务类型:</label>
                                    <input type="text" class="form-control" name="bizType"
                                           value="${param.bizType}"/>
                                </div>
                                <div class='form-group col-md-6'>
                                    <label class="control-label">业务主键:</label>
                                    <input type="text" class="form-control" name="bizKey"
                                           value="${param.bizKey}"/>
                                </div>
                            </div>

                            <div class="row">
                                <div class='form-group col-md-6'>
                                    <label class="control-label">开始时间:</label>
                                    <input id="createTimeStart" type="text" class="form-control"
                                           name="createTimeStart"
                                           value="${param.createTimeStart}"/>
                                </div>
                                <div class='form-group col-md-6'>
                                    <label class="control-label">结束时间:</label>
                                    <input id="createTimeEnd" type="text" class="form-control" name="createTimeEnd"
                                           value="${param.createTimeEnd}"/>
                                </div>

                            </div>
                        </div>
                        <div class='form-group col-md-2'>
                            <button class='btn' type='button' value="查询" onclick="taskSearch()" title="查询">
                                <i class='glyphicon glyphicon-search'></i>
                            </button>
                        </div>
                    </fieldset>
                </div>
            </form>
        </div>
        <table class='table'>
            <c:if test="${empty exceptionList}">
                <tr>
                    <td><span>没有数据！</span></td>
                </tr>
            </c:if>

            <thead>
            <tr>
                <th>序号</th>
                <th>任务类型</th>
                <th>业务主键</th>
                <th>创建时间</th>
                <th class='actions'>
                    操作
                </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${exceptionList}" varStatus="status" var="each">
                <tr class=''>
                    <td>${status["index"] + 1}</td>
                    <td>${each["bizType"]}</td>
                    <td>${each["bizKey"]}</td>
                    <td>${each["createTime"]}</td>
                    <td class='action'>
                        <button class='btn btn-danger' type='button' onclick="del(${each["id"]})"
                                title="删除">
                            <i class='glyphicon glyphicon-trash'></i>
                        </button>
                        <button class='btn btn-primary' type='button' onclick="move(${each["id"]})"
                                title="补偿">
                            <i class='glyphicon glyphicon-refresh'></i>
                        </button>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>

        <%@include file="./common/page.jsp" %>
    </div>
</div>
</body>
</html>
