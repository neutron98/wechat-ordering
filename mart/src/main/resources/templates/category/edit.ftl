<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

    <#--Sidebar-->
    <#include "../common/nav.ftl">

    <#--Main content-->
    <h3 class="text-center"> Add/ Edit a Category</h3><br><br>
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-6 column col-md-offset-3">
                    <form role="form" method="post" action="/sell/seller/category/save">
                        <div class="form-group">
                            <label >Name</label>
                            <input name="categoryName" type = "text" class="form-control"
                                   value="${(categoryInfo.categoryName)!""}"/>
                        </div>
                        <div class="form-group">
                            <label>Type</label>
                            <input type="number" class="form-control"
                            <#if (categoryInfo.categoryType)??>
                            <#-- If type is not null, then it is readonly (protect it from edit)-->
                                readonly
                            </#if>
                            name="categoryType" value="${(categoryInfo.categoryType)!''}"/>
                        </div>

                        <input type="hidden" name="categoryId" value="${(categoryInfo.categoryId)!""}">
                        <div>
                            <br><button type="submit" class="btn btn-default center-block">Submit</button>
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>