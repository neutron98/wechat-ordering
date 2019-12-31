
<html>
<#include "../common/header.ftl">

<body>

      <#-- Wrapper-->
<div id="wrapper" class="toggled">
    <#-- Side bar -->
    <#include "../common/nav.ftl">
    <#-- Main content -->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>Order Id</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Phone Number</th>
                            <th>Address</th>
                            <th>Amount</th>
                            <th>Order Status</th>
                            <th>Payment Status</th>
                            <th>Create Time</th>
                            <th colspan="2">Options</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderDTOPage.content as orderDTO>
                            <tr>
                                <td>${orderDTO.orderId}</td>
                                <td>${orderDTO.firstName}</td>
                                <td>${orderDTO.lastName}</td>
                                <td>${orderDTO.consumerPhone}</td>
                                <td>${orderDTO.consumerAddress}</td>
                                <td>${orderDTO.orderAmount}</td>
                                <td>${orderDTO.getOrderStatusEnum().getMessage()}</td>
                                <td>${orderDTO.getPayStatusEnum().getMessage()}</td>
                                <td>${orderDTO.createTime}</td>
                                <td><a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}">detail</a>
                                </td>
                                <td>
                                    <#if orderDTO.getOrderStatusEnum().getMessage()  == "new order">
                                        <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">cancel</a>
                                    </#if>
                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>

                <#--pagination-->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                        <#if currentPage lte 1>
                            <li class = "disabled"><a href="#">Prev</a></li>
                        <#else>
                            <li><a href="http://mart.com/sell/seller/order/list?page=${currentPage - 1}&size=${size}">Prev</a></li>
                        </#if>

                        <#list 1..orderDTOPage.getTotalPages() as index>
                            <#if currentPage == index>
                                <li class="disabled"><a href="#">${index}</a></li>
                            <#else>
                                <li><a href="http://mart.com/sell/seller/order/list?page=${index}&size=${size}">${index}</a></li>
                            </#if>
                        </#list>

                        <#if currentPage gte orderDTOPage.getTotalPages()>
                            <li class = "disabled"><a href="#">Next</a></li>
                        <#else>
                            <li><a href="http://mart.com/sell/seller/order/list?page=${currentPage + 1}&size=10">Next</a></li>
                        </#if>
                    </ul>
                </div>

            </div>
        </div>
    </div>
</div>


<#-- Alert-->
<div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
      <div class="modal-content">
          <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
              <h4 class="modal-title" id="myModalLabel">
                  Notify
              </h4>
          </div>
          <div class="modal-body">
              You received a new order.
          </div>
          <div class="modal-footer">
              <button onclick="javascript: document.getElementById('notice').pause()" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
              <button onclick="location.reload()" type="button" class="btn btn-primary">Check the order</button>
          </div>
      </div>

  </div>
</div>

<#--play music-->
<audio id="notice" loop="loop" autoplay="true" muted>
    <source src="/sell/mp3/song.mp3" type = "audio/mpeg"/>
</audio>

      <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
      <script>
    var webSocket = null;
    if ('WebSocket' in window){
        webSocket = new WebSocket('ws://mart.natapp1.cc/sell/webSocket');
    } else{
        alert('The browser does not support websocket!')
    }
    webSocket.onopen = function (event){
        console.log('Built connection');
    }
    webSocket.onclose = function(event){
        console.log('Connection closed')
    }

    webSocket.onmessage = function (event) {
        console.log('Received message: ' + event.data);

        // alert and play music
        $('#myModal').modal('show');

        document.getElementById('notice').play();
        document.getElementById('notice').muted = false;

    }

    webSocket.onerror= function () {
        alert(('Error in websocket messaging!'))
    }

    window.onbeforeunload = function(){
        webSocket.close();
    }

</script>
</body>

</html>
