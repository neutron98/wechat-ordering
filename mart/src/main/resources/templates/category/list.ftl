<html>
<head>
    <#include "../common/header.ftl">

</head>
<body>
<div id="wrapper" class="toggled">
    <#--Sidebar-->
    <#include "../common/nav.ftl">
    <#--Main content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <!--Table-->
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>Category ID</th>
                            <th>Category Name</th>
                            <th>Category Number</th>
                            <th>Create Time</th>
                            <th>Update Time</th>
                            <th>Options</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list categoryList as category>
                            <tr>
                                <td>${category.categoryId}</td>
                                <td>${category.categoryName}</td>
                                <td>${category.categoryType}</td>
                                <td>${category.createTime}</td>
                                <td>${category.updateTime}</td>
                                <td><a href="/sell/seller/category/edit?categoryId=${category.categoryId}">Edit</a></td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>