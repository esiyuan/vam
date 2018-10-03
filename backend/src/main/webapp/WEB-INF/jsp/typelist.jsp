<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html class='no-js' lang='en'>
<head>

    <meta charset='utf-8'>
    <meta content='IE=edge,chrome=1' http-equiv='X-UA-Compatible'>
    <title>Tables</title>

    <link href="/assets/stylesheets/application-a07755f5.css" rel="stylesheet" type="text/css"/>
    <link href="/assets/images/favicon.ico" rel="icon" type="image/ico"/>
    <script src="/assets/javascripts/jquery.min.js.js"></script>
    <script src="/assets/javascripts/typeList.js"></script>
</head>
<body class=''>
<div id=''>
    <div class='panel panel-default grid'>
        <div class='panel-body filters'>
            <form id="typeListForm" method="post">
                <div class='row' style="padding-right: 10%">
                    <div class='col-md-9'>
                    </div>
                    <div class='col-md-3'>
                        <div class='input-group'>
                            <input id="typeListPageStart" hidden="hidden" name="start" type='text'
                                   value="${page["start"]}"/>
                            <input class='form-control' placeholder='任务类型' type='text'>
                            <span class='input-group-btn'>
                    <button class='btn' type='button' onclick="search(1)">
                      <i class='icon-search'>查询</i>
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
                <th>状态</th>
                <th>超时时间</th>
                <th class='actions'>
                    Actions
                </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${typeList}" varStatus="status" var="each">

                <tr class=''>
                    <td>${status["index"]}</td>
                    <td>${each["bizType"]}</td>
                    <td>${each["maxRunTimes"]}</td>
                    <td>${each["status"]}</td>
                    <td>${each["timeoutMinutes"]}</td>
                    <td class='action'>
                        <a class='btn btn-info' data-toggle='tooltip' href='update?id=${each["id"]}' title='Zoom'>
                            <i class='icon-zoom-in'>修改</i>
                        </a>
                        <a class='btn btn-info' href='#'>
                            <i class='icon-edit'></i>
                        </a>
                        <a class='btn btn-danger' href='#'>
                            <i class='icon-trash'>删除</i>
                        </a>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
        <div class='panel-footer' style="padding-left: 70%;">
            <div class='pull-left'>
                展示${page.indexStart} 到 ${page.indexEnd} 条 / 总数 ${page.totalCount}
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
<!-- Footer -->


</body>
</html>
