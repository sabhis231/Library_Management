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
                    <div class="row" id="top-data">

                    </div>
                    <div class="row">
                        <div class="col-lg-12 col-md-12">
                            <div class="card">
                                <div class="card-header card-header-primary">
                                    <h4 class="card-title">Available Books</h4>
                                    <p class="card-category">Books as on <span class="current-date"></span></p>
                                </div>
                                <div class="card-body table-responsive">
                                    <table class="table table-hover" id="all-book">

                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6 col-md-12">
                            <div class="card">
                                <div class="card-header card-header-warning">
                                    <h4 class="card-title">Borrowed Book</h4>
                                    <p class="card-category">Books as on <span class="current-date"></span></p>
                                </div>
                                <div class="card-body table-responsive">
                                    <table class="table table-hover" id="borrowed-book">

                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6 col-md-12">
                            <div class="card">
                                <div class="card-header card-header-warning">
                                    <h4 class="card-title">Requested Book</h4>
                                    <p class="card-category">Books as on <span class="current-date"></span></p>
                                </div>
                                <div class="card-body table-responsive">
                                    <table class="table table-hover" id="requested-book">
                                      
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
                    <h5 class="modal-title" id="exampleModalLabel">Edit Book</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <!--<div class="col-md-8">-->
                    <div class="card">
                        <div class="card-header card-header-primary">
                            <h4 class="card-title">Edit Book</h4>
                            <!--<p class="card-category">Edit</p>-->
                        </div>
                        <div class="card-body">
                            <form method="post" id="updateOrSaveBook">
                                <input type="text" class="form-control" id="bookId" name="bookId" value="0" hidden>
                                <div class="row">
                                    <div class="col-md-5">
                                        <div class="form-group">
                                            <label class="bmd-label-floating">Book Title</label>
                                            <input type="text" class="form-control" id="booktitle" name="booktitle">
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="bmd-label-floating">Author</label>
                                            <select class="form-control" id="authorList" name="authorList">
                                                <option selected disabled>Select Author</option>

                                            </select>

                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="bmd-label-floating">Publisher</label>
                                            <select class="form-control" id="publisherList" name="publisherList">
                                                <option selected disabled>Select Publisher</option>

                                            </select>

                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="bmd-label-floating">Published On </label>
                                            <input type="date" class="form-control" id="publishedYear" name="publishedYear">
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="bmd-label-floating">Total Pages</label>
                                            <input type="text" class="form-control" id="totalPages" name="totalPages">
                                        </div>
                                    </div>

                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label>About</label>
                                            <div class="form-group">
                                                <textarea class="form-control" rows="5" id="description" name="description"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-primary pull-right">Update Details</button>
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
    <script src="../../Assets/js/admin-index.js?v1" type="text/javascript"></script>
    <script src="../../Assets/js/global-script.js?v1" type="text/javascript"></script>
</body>