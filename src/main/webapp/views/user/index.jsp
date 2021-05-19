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
                                    <table class="table table-hover">
                                        <thead class="text-warning">
                                        <th>Book Name</th>
                                        <th>Publisher</th>
                                        <th>Published Year</th>
                                        <th>Total pages</th>
                                        <th class="text-center">Borrow Book</th>
                                        </thead>
                                        <tbody id="all-books">

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6 col-md-12">
                            <div class="card">
                                <div class="card-header card-header-primary">
                                    <h4 class="card-title">Borrowed Book</h4>
                                    <p class="card-category">Books as on <span class="current-date"></span></p>
                                </div>
                                <div class="card-body table-responsive">
                                    <table class="table table-hover">
                                        <thead class="text-warning">
                                        <th>Book Name</th>
<!--                                        <th>Publisher</th>
                                        <th>Published Year</th>-->
                                        <!--<th>Total Pages</th>-->
                                        <th>Borrowed On</th>
                                        <th>Due On</th>
                                        <th>Return</th>
                                        </thead>
                                        <tbody id="borrowed-book" >

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6 col-md-12">
                            <div class="card">
                                <div class="card-header card-header-primary">
                                    <h4 class="card-title">Requested Book</h4>
                                    <p class="card-category">Books as on <span class="current-date"></span></p>
                                </div>
                                <div class="card-body table-responsive">
                                    <table class="table table-hover">
                                        <thead class="text-warning">
                                        <th>Book Name</th>
<!--                                        <th>Publisher</th>
                                        <th>Published Year</th>-->
                                        <!--<th>Total pages</th>-->
                                        <th>Requested On </th>
                                        <th>Cancel Request</th>
                                        </thead>
                                        <tbody id="requested-book" >

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
    <script src="../../Assets/js/user-index.js" type="text/javascript"></script>
    <script src="../../Assets/js/global-script.js" type="text/javascript"></script>
</body>