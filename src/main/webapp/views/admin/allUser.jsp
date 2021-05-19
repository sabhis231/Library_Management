<!--
=========================================================
* Material Dashboard Dark Edition - v2.1.0
=========================================================

* Product Page: https://www.creative-tim.com/product/material-dashboard-dark
* Copyright 2019 Creative Tim (http://www.creative-tim.com)

* Coded by www.creative-tim.com

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
-->
<%@include file="../inc/header.jsp" %>

<body class="dark-edition">
    <div class="wrapper ">
        <%@include file="../inc/menu.jsp" %>
        <div class="main-panel">
            <!-- Navbar -->
            <%@include file="../inc/upper-navigation.jsp" %>
            <!-- End Navbar -->
            <div class="content">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12 col-md-12">
                            <button type="button" class="btn btn-primary pull-right add-user" data-toggle="modal" data-target="#showandedit">Add User</button>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12 col-md-12">
                            <div class="card">
                                <div class="card-header card-header-primary">
                                    <h4 class="card-title">User List</h4>
                                    <p class="card-category">User as on <span class="current-date"></span></p>
                                </div>
                                <div class="card-body table-responsive">
                                    <table class="table table-hover" id="user-list">

                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="showandedit" tabindex="-1" role="dialog" aria-labelledby="showandedit" aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">New User</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <!--<div class="col-md-8">-->
                    <div class="card">
                        <div class="card-header card-header-primary">
                            <h4 class="card-title" id="card-model">New User</h4>
                            <!--<p class="card-category">Edit</p>-->
                        </div>
                        <div class="card-body">
                            <form method="post" id="updateOrSaveUser">
                                <input type="text" class="form-control" id="userId" name="userId" value="0" hidden>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="bmd-label-floating">User Name</label>
                                            <input type="text" class="form-control" id="userName" name="userName" required>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="bmd-label-floating">Email</label>
                                            <input type="text" class="form-control" id="email" name="email" required>
                                        </div>
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-primary pull-right button-submit">add user</button>
                                <div class="clearfix"></div>
                            </form>
                        </div>
                        <!--</div>-->
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal" id="hideModel">Close!</button>
                </div>
            </div>
        </div>
    </div>



    <div class="modal fade" id="disabledUserConfirm" tabindex="-1" role="dialog" aria-labelledby="disabledAuthorConfirm" aria-hidden="true">
        <div class="modal-dialog modal-md" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="list-model-title">Delete User</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-lg-12 col-md-12">
                            <div class="card">
                                <div class="card-header card-header-primary">
                                    <h4 class="card-title" id="list-model-card-title">Want to Delete?</h4>
                                    <p class="card-category"><span id="list-model-card-sub-title">Delete  as on</span> <span class="current-date"></span></p>
                                </div>
                                <div class="card-body ">
                                    <p> Do you really want to Delete the User? 
                                        <br>
                                        Once the operation is performed it can not be reverted back.</p>

                                    <button type="button" class="btn btn-primary pull-right" id="user-disabled">Delete User</button>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary hideModel" data-dismiss="modal" >Close!</button>
                </div>
            </div>
        </div>
    </div>

    
    <div class="modal fade" id="resetPassword" tabindex="-1" role="dialog" aria-labelledby="resetPassword" aria-hidden="true">
        <div class="modal-dialog modal-md" role="document">
            <div class="modal-content">
                <div class="modal-header">
                   <h5 class="modal-title" id="list-model-title">Reset Password</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-lg-12 col-md-12">
                            <div class="card">
                                <div class="card-header card-header-primary">
                                    <h4 class="card-title" id="list-model-card-title">Want to Reset password?</h4>
                                    <p class="card-category"><span id="list-model-card-sub-title">Pass reset  as on</span> <span class="current-date"></span></p>
                                </div>
                                <div class="card-body ">
                                    <p> Do you really want to reset user password? 
                                        <br>
                                        Once the operation is performed it can not be reverted back.</p>

                                    <button type="button" class="btn btn-primary pull-right" id="user-reset">Reset password</button>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary hideModel" data-dismiss="modal" >Close!</button>
                </div>
            </div>
        </div>
    </div>
    
    
    <script src="../../Assets/js/admin-allUser.js?v1" type="text/javascript"></script>
    <script src="../../Assets/js/global-script.js" type="text/javascript"></script>
</body>