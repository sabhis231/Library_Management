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
                            <div class="input-group no-border ">

                            </div>

                        </div>
                        <div class="col-lg-12 col-md-12">

                            <button type="button" class="btn btn-primary pull-right add-book" data-toggle="modal" data-target="#showandedit">Add Book</button>

                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12 col-md-12">
                            <div class="card">
                                <div class="card-header card-header-primary">
                                    <h4 class="card-title">Available Books</h4>
                                    <p class="card-category">Books as on <span class="current-date"></span></p>
                                </div>
                                <div class="card-body table-responsive">
                                    <table class="table table-hover">
                                        <thead class="text-warning">
                                        <th>Name</th>
                                        <th>Publisher</th>
                                        <th>Published Year</th>
                                        <th>Total pages</th>
                                        <th>Book Status</th>
                                        <th colspan="3" style="text-align:center">Action</th>
                                        </thead>
                                        <tbody id="all-books">

                                        </tbody>
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
                            <h4 class="card-title" id="card-model">Edit Book</h4>
                            <!--<p class="card-category">Edit</p>-->
                        </div>
                        <div class="card-body">
                            <form method="post" id="updateOrSaveBook">
                                <input type="text" class="form-control" id="bookId" name="bookId" value="0" hidden>
                                <div class="row">
                                    <div class="col-md-5">
                                        <div class="form-group">
                                            <label class="bmd-label-floating">Book Title</label>
                                            <input type="text" class="form-control" id="booktitle" name="booktitle" required>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="bmd-label-floating">Author</label>
                                            <select class="form-control" id="authorList" name="authorList" required>
                                                <option disabled>Select Author</option>

                                            </select>

                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="bmd-label-floating">Publisher</label>
                                            <select class="form-control" id="publisherList" name="publisherList" required>
                                                <option disabled>Select Publisher</option>

                                            </select>

                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="bmd-label-floating">Published On </label>
                                            <input type="date" class="form-control" id="publishedYear" name="publishedYear" required>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="bmd-label-floating">Total Pages</label>
                                            <input type="text" class="form-control" id="totalPages" name="totalPages" required>
                                        </div>
                                    </div>

                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label>About</label>
                                            <div class="form-group">
                                                <textarea class="form-control" rows="5" id="description" name="description" required></textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-primary pull-right" id="form-button-book">Update Details</button>
                                <div class="clearfix"></div>
                            </form>
                        </div>
                        <!--</div>-->
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" id="deleteButton">Delete!</button>
                    <button type="button" class="btn btn-secondary hideModel" data-dismiss="modal" id="hideModel">Close!</button>
                </div>
            </div>
        </div>
    </div>



    <div class="modal fade" id="viewList" tabindex="-1" role="dialog" aria-labelledby="showandedit" aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="list-model-title">Request List</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-lg-12 col-md-12">
                            <div class="card">
                                <div class="card-header card-header-primary">
                                    <h4 class="card-title" id="list-model-card-title">Request User List</h4>
                                    <p class="card-category"><span id="list-model-card-sub-title">Request List  as on</span> <span class="current-date"></span></p>
                                </div>
                                <div class="card-body table-responsive">
                                    <table class="table table-hover" id="user-List">

                                    </table>
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

    <script src="../../Assets/js/admin-allBooks.js?v1" type="text/javascript"></script>
    <script src="../../Assets/js/global-script.js" type="text/javascript"></script>
</body>