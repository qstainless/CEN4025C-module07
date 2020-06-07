<!-- New Item Modal -->
<div class="modal fade" id="newItemModal" tabindex="-1" role="dialog" aria-labelledby="newItemModal" aria-hidden="true">

    <div class="modal-dialog">

        <div class="modal-content">

            <div class="modal-header bg-primary">
                <h5 class="modal-title text-white" id="newItemModalLabel">Add New Item</h5>
                <button type="button" class="close text-white" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">

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
                            <button type="button" class="btn btn-sm btn-warning" data-dismiss="modal"
                                    aria-label="Cancel">
                                Cancel
                            </button>
                        </div> <!-- /.col -->

                    </div> <!-- /.form-group -->

                </form>

            </div> <!-- modal-body -->

        </div> <!-- modal-content -->

    </div> <!-- modal-dialog -->

</div>
<!-- modal -->

