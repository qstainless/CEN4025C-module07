<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<%@include file="common/header.jsp" %>

<body>

<section>
    <div class="m-5">
        <%@ include file="common/heading.jsp" %>

        <div class="row">

            <div class="col">

                <div class="card border-primary">

                    <div class="card-header bg-danger py-1">
                        <h3 class="text-white m-0">WARNING</h3>
                    </div> <!-- /.card-header -->

                    <div class="card-body p-2">

                        <!-- Confirmation -->

                        <p>You are about to delete the to-do item:</p>
                        <h4 class="py-3 pl-5"><c:out value="${item.itemDescription}"/></h4>
                        <p>This action cannot be undone.</p>

                        <hr>

                        <p>Click DELETE to confirm deletion or Cancel to go back.</p>

                        <!-- Buttons -->

                        <a href="<%=request.getContextPath()%>/delete?id=<c:out value='${item.id}' />"
                           class="btn btn-sm btn-danger float-right">DELETE</a>
                        <a href="<%=request.getContextPath()%>/list"
                           class="btn btn-sm btn-success">Cancel</a>

                    </div> <!-- /.card-body -->

                </div> <!-- /.card -->

            </div> <!-- /.col -->

        </div> <!-- /.row -->

    </div>
</section>

</body>
</html>
