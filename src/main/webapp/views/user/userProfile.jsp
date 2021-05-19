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
                        <div class="col-md-8">
                            <div class="card">
                                <div class="card-header card-header-primary">
                                    <h4 class="card-title">Edit Profile</h4>
                                    <p class="card-category">Complete your profile</p>
                                </div>
                                <div class="card-body">
                                    <form method="post" id="updateProfile">
                                        <div class="row">
                                            <div class="col-md-5">
                                                <div class="form-group">
                                                    <label class="bmd-label-floating">Title</label>
                                                    <input type="text" class="form-control" disabled value="Mr./Mrs.">
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="bmd-label-floating">Name</label>
                                                    <input type="text" class="form-control" id="name" name="name" required>
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="bmd-label-floating">Email address</label>
                                                    <input type="email" class="form-control" id="emailId" name="emailId" required>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="bmd-label-floating">Date of Birth</label>
                                                    <input type="date" class="form-control" id="dob" name="dob" required>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="bmd-label-floating">Gender</label>
                                                    <select class="form-control" id="gender" name="gender" required>
                                                        <option selected disabled>Select Gender</option>
                                                        <option value="Male">Male</option>
                                                        <option value="FeMale">FeMale</option>
                                                        <option value="Not to Say">Not to Say </option>
                                                    </select>

                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="bmd-label-floating">City</label>
                                                    <input type="text" class="form-control" id="city" name="city" required>
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="bmd-label-floating">Country</label>
                                                    <input type="text" class="form-control" id="country" name="country" required>
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="bmd-label-floating">Zip Code</label>
                                                    <input type="text" class="form-control" id="zipCode" name="zipCode" required>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="form-group">
                                                    <label>About Me</label>
                                                    <div class="form-group">
                                                        <!--                                                        <label class="bmd-label-floating"> 
                                                                                                                    Lamborghini Mercy, Your chick she so thirsty, I'm in that two seat Lambo.\
                                                                                                                </label>-->
                                                        <textarea class="form-control" rows="5" id="about" name="about" required></textarea>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <button type="submit" class="btn btn-primary pull-right">Update Profile</button>
                                        <div class="clearfix"></div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card card-profile">
                                <div class="card-avatar">
                                    <a href="#pablo">
                                        <img class="img" src="${contextPath}/Assets/plugins/new-material-theme/img/faces/marc.jpg" />
                                    </a>
                                </div>
                                <div class="card-body">
                                    <h4 class="card-title" id="userName">{{Name}}</h4>
                                    <p class="card-description" id="aboutDesc">
                                        {{about}}
                                    </p>
                                    <a class="btn btn-primary btn-round" data-toggle="modal" data-target="#exampleModal">Deactivate Account</a>
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
                    <h5 class="modal-title" id="exampleModalLabel">Deactivate Account</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    Do you Really want to Deactivate the Account?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal" id="hideModel">No!</button>
                    <a type="button" class="btn btn-primary" id="deactiveButton">Yes!</a>
                </div>
            </div>
        </div>
    </div>
    <script src="../../Assets/js/userProfile.js" type="text/javascript"></script>
    <script src="../../Assets/js/global-script.js" type="text/javascript"></script>
</body>