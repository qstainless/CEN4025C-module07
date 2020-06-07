<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<%@include file="header.jsp" %>

<body>

<section>
    <div class="m-5">
        <%@ include file="heading.jsp" %>

        <div class="clearfix"></div>

        <div class="row">

            <div class="col-md-12 col-lg-6">

                <div class="card border-primary">

                    <div class="card-header bg-primary py-0">
                        <h3 class="text-white">Add new item</h3>
                    </div>

                    <div class="card-body p-2">

                        <div id="container">

                            <form action="insert" method="post">

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
                                               maxlength="250"
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
                                        <textarea id="itemDetails"
                                                  name="itemDetails"
                                                  type="text"
                                                  class="form-control"
                                                  rows="10"
                                                  cols="80"
                                                  tabindex="3"
                                                  required></textarea>
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
                                               tabindex="5"
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
                                        <a href="list" class="btn btn-sm btn-warning">Cancel</a>
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
