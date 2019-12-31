<html>
<#include "../common/header.ftl">


<body>
<#-- Wrapper-->
<div id="wrapper" class="toggled">
    <#-- Side bar -->
    <#include "../common/nav.ftl">
    <#-- Main content -->
    <div id="page-content-wrapper">
        <div class="container">
    <div class="row clearfix">

        <#-- Order Master-->
        <div class="col-md-4 column">
            <table class="table">
                <thead>
                <tr>
                    <th>
                        Order Id
                    </th>
                    <th>
                        Order Amount
                    </th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>
                        ${orderDTO.orderId}
                    </td>
                    <td>
                        ${orderDTO.orderAmount}
                    </td>
                </tr>
                </tbody>
            </table>
        </div>

        <div class="col-md-12 column">
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th>Product Id</th>
                    <th>Product Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Subtotal</th>
                </tr>
                </thead>
                <tbody>
                <#list orderDTO.orderDetailList as orderDetail>
                    <tr>
                        <td>${orderDetail.productId}</td>
                        <td>${orderDetail.productName}</td>
                        <td>${orderDetail.productPrice}</td>
                        <td>${orderDetail.productQuantity}</td>
                        <td>${orderDetail.productPrice * orderDetail.productQuantity}</td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>

        <#if orderDTO.getOrderStatusEnum().getMessage()  == "new order">

        <div class="col-md-6 column">
            <a href="/sell/seller/order/finish?orderId=${orderDTO.orderId}" type="button" class="btn btn-default btn-primary">Finish</a>
            <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}" type="button" class="btn btn-default btn-danger">Cancel</a>
        </div>
        </#if>

    </div>
</div>
    </div>
</div>
</body>
</html>