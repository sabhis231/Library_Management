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
                            <div class="card">
                                <div class="card-header card-header-primary">
                                    <h4 class="card-title">Borrowed Book</h4>
                                    <p class="card-category">Books as on <span class="current-date"></span></p>
                                </div>
                                <div class="card-body table-responsive">
                                    <table class="table table-hover" id="borrowed-book">

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
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Book Detail</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <p> 
                        Book Name : <span id="bookName"></span>
                    </p>
                    <p>
                        Publisher Name : <span id="publisherName"></span>
                    </p>
                    <p>
                        Published On  : <span id="publishedOn"></span>
                    </p>
                    <p>
                        Author : <span id="authorName"></span>
                    </p>
                    <p>
                        No. of Pages : <span id="pages"></span>
                    </p>
                    <p>
                        User : <span id="userDetails"></span>
                    </p>
                    <h5 class="highlight"></h5>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal" id="hideModel">No!</button>
                    <a type="button" class="btn btn-primary" id="returnOrRenewBook">Yes!</a>
                </div>
            </div>
        </div>
    </div>
    <script src="../../Assets/js/admin-allBorrowedBook.js?v1" type="text/javascript"></script>
    <script src="../../Assets/js/global-script.js" type="text/javascript"></script>
</body>