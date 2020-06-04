<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<%@include file="header.jsp" %>

<body>

<section>
    <div class="m-5">

        <h1 class="tex-center">To-do List</h1>
        <h6 class="float-right px-2">Guillermo Castaneda Echegaray<br>CEN4025C - Module 7 Assignment</h6>

        <div class="row">

            <div class="col-md-12 col-lg-6 ">

                <div class="card border-primary mb-3">

                    <div class="card-header bg-primary py-1">
                        <h3 class="text-white m-0">Add new item</h3>
                    </div>

                    <div class="card-body p-2">

                        <div id="container">

                            <form method="post"
                                  action="insert"
                                  onsubmit='document.getElementById("create").disabled = true;'>

                                <!-- Item Description -->

                                <div class="form-group row">

                                    <label for="itemDescription"
                                           class="col-sm-3 col-form-label text-primary font-weight-normal">
                                        Description
                                    </label>

                                    <div class="col-sm-9">
                                        <input id="itemDescription"
                                               name="itemDescription"
                                               type="text"
                                               class="form-control"
                                               maxlength="255"
                                               required
                                               tabindex="1"
                                               autofocus/>
                                    </div> <!-- /.col -->
                                </div> <!-- /.form-group -->


                                <!-- Item Details -->

                                <div class="form-group row">

                                    <label for="itemDetails"
                                           class="col-sm-3 col-form-label text-primary font-weight-normal">
                                        Details
                                    </label>

                                    <div class="col-sm-9">
                                        <input id="itemDetails"
                                               name="itemDetails"
                                               type="text"
                                               class="form-control"
                                               maxlength="255"
                                               required
                                               tabindex="1"
                                               autofocus/>
                                    </div> <!-- /.col -->
                                </div> <!-- /.form-group -->


                                <!-- Item Due Date -->

                                <div class="form-group row">

                                    <label for="itemDueDate"
                                           class="col-sm-3 col-form-label text-primary font-weight-normal">
                                        Due Date
                                    </label>

                                    <div class="col-sm-9">
                                        <input id="itemDueDate"
                                               name="itemDueDate"
                                               type="text"
                                               class="form-control datetimepicker-input"
                                               data-target="#itemDueDate"
                                               data-toggle="datetimepicker"
                                               required
                                               tabindex="3"
                                               readonly="readonly"/>
                                        <script type="text/javascript">
                                            $(function () {
                                                $('#itemDueDate').datetimepicker({
                                                    format: 'YYYY-MM-DD',
                                                    ignoreReadonly: true
                                                });
                                            });
                                        </script>
                                    </div> <!-- /.col -->
                                </div> <!-- /.form-group -->


                                <!-- Buttons -->
                                <div class="form-group row">

                                    <label for="create"
                                           class="col-sm-3 col-form-label font-weight-normal">&nbsp;</label>

                                    <div class="col-sm-9">
                                        <button id="create" type="submit" class="btn btn-sm btn-primary">Create</button>&nbsp;
                                        <a href="index.jsp" class="btn btn-sm btn-warning">Cancel</a>
                                    </div> <!-- /.col -->

                                </div> <!-- /.form-group -->

                            </form>

                        </div> <!-- /.container -->

                    </div> <!-- /.card-body -->

                </div> <!-- /.card -->

            </div> <!-- /.col -->

        </div> <!-- row -->

    </div>

</section>

</body>
</html>
