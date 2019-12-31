<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

    <#--Sidebar-->
    <#include "../common/nav.ftl">
    <h3 class="text-center"> Add/ Edit a Product</h3><br><br>
    <#--Main content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-6 column col-md-offset-3">
                    <form role="form" method="post" action="/sell/seller/product/save">
                        <div class="form-group">
                            <label >Name</label>
                            <input name="productName" type = "text" class="form-control"
                                   value="${(productInfo.productName)!""}"/>
                        </div>
                        <div class="form-group">
                            <label>Price</label>
                            <input name="productPrice" type = "text" class="form-control"
                                   value="${(productInfo.productPrice)!""}"/>
                        </div>

                        <div class="form-group">
                            <label>Stock</label>
                            <input name="productStock" type = "number" class="form-control"
                                   value="${(productInfo.productStock)!""}"/>
                        </div>
                        <div class="form-group">
                            <label>Description</label>
                            <input name="productDescription" type = "text" class="form-control"
                                   value="${(productInfo.productDescription)!""}"/>
                        </div>
                        <#-- Image -->
                        <div class="form-group">
                            <label>Image</label>
                            <img class = "center-block" height="100" width="100" src="${(productInfo.productIcon)!""}" alt="">
                            <br><input name="productIcon" type = "text" class="form-control"
                                   value="${(productInfo.productIcon)!''}"/>
                        </div>

                        <div class="form-group">
                            <label>Category</label>
                            <select name="categoryType" class="form-control">
                                <#list categoryList as category>
                                    <option value="${category.categoryType}"
                                    <#if (productInfo.categoryType)?? && productInfo.categoryType == category.categoryType>
                                        selected
                                    </#if>
                                    >
                                        ${category.categoryName}
                                    </option>
                                </#list>
                            </select>
                        </div>
                        <input type="hidden" name="productId" value="${(productInfo.productId)!""}">
                        <br><button type="submit" class="btn btn-default center-block">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>