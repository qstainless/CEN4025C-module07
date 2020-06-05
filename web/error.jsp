<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<%@include file="header.jsp" %>

<body>

<section>
    <div class="m-5">
        <h1 class="tex-center">To-do List</h1>
        <h6 class="float-right px-2">Guillermo Castaneda Echegaray<br>CEN4025C - Module 7 Assignment</h6>
        <a href="index.jsp" class="btn btn-warning btn-sm my-2">Go back</a>

        <div class="clearfix"></div>

        <div class="row">

            <div class="col-md-12 col-lg-6">

                <div class="card border-danger">

                    <div class="card-header bg-danger py-0">
                        <h3 class="text-white">An error ocurred</h3>
                    </div>

                    <div class="card-body p-2">

                        <div id="container">

                            <p><%=exception.getMessage() %></p>

                        </div> <!-- /.container -->

                    </div> <!-- /.card-body -->

                </div> <!-- /.card -->

            </div> <!-- /.col -->

        </div> <!-- row -->

    </div>
</section>

</body>
</html>
