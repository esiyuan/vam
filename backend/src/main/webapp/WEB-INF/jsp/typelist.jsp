<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html class='no-js' lang='en'>
<head>

    <meta charset='utf-8'>
    <meta content='IE=edge,chrome=1' http-equiv='X-UA-Compatible'>
    <title>任务类型管理</title>

    <link href="/assets/stylesheets/application-a07755f5.css" rel="stylesheet" type="text/css"/>
    <link href="/assets/images/favicon.ico" rel="icon" type="image/ico"/>
    <link rel="stylesheet" href="/assets/page/pagination.css">
    <script src="/assets/javascripts/jquery.min.js.js"></script>
    <script src="/assets/page/pagination.js"></script>
    <script src="/assets/page/page.js"></script>
    <script src="/assets/javascripts/typeList.js"></script>
</head>
<body class=''>
<div id=''>
    <div class='panel panel-default grid'>
        <div class='panel-body filters'>
            <form id="typeListForm" method="post" action="search.htm">
                <div class='row' style="padding-right: 10%">
                    <div class='col-md-9'>
                    </div>
                    <div class='col-md-3'>
                        <div class='input-group'>
                                            <span class='input-group-btn'>
                            <a class='btn btn-info' href='new.htm'>
                                <i class='glyphicon glyphicon-plus-sign'>新增</i>
                            </a>
                            </span>
                            <%@include file="./common/cur_index.jsp" %>
                            <input class='form-control' id="searchBizType" name="bizType" value="${param['bizType']}"
                                   placeholder='任务类型'
                                   type='text'>

                            <span class='input-group-btn'>
                                <button class='btn' type='button' onclick="taskSearch(1)" title="查询">
                                    <i class='glyphicon glyphicon-search'></i>
                                </button>
                            </span>

                        </div>
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
                <th>最大执行次数</th>
                <th>重试间隔</th>
                <th>超时时间(分)</th>
                <th>单次任务数</th>
                <th>状态</th>
                <th class='actions'>
                    操作
                </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${typeList}" varStatus="status" var="each">
                <tr class=''>
                    <td>${status["index"] + 1}</td>
                    <td>${each["bizType"]}</td>
                    <td>${each["maxRunTimes"]}</td>
                    <td>${each["runIntervals"]}</td>
                    <td>${each["timeoutMinutes"]}</td>
                    <td>${each["perPageCount"]}</td>
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
                        <a class='btn btn-info' data-toggle='tooltip' href='update.htm?id=${each["id"]}' title='修改'>
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
        <%@include file="./common/page.jsp" %>
    </div>
</div>


</body>
</html>
