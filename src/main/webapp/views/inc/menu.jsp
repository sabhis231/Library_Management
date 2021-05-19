<%-- 
    Document   : menu.jsp
    Created on : 15-May-2021, 19:19:28
    Author     : sabhis231
--%>
<c:if test="${empty sessionScope.Role}">
    <canvas id="pixie"></canvas>
    </c:if>

<div class="pagehead">
    <div class="container">
        <c:if test="${sessionScope.Role=='admin'}">
            <div class="sidebar" 
                 data-color="purple" 
                 data-background-color="black" 
                 data-image="${contextPath}/Assets/plugins/new-material-theme/img/sidebar-2.jpg">
                <!--
                  Tip 1: You can change the color of the sidebar using: data-color="purple | azure | green | orange | danger"
          
                  Tip 2: you can also add an image using data-image tag
                -->
                <div class="logo"><a href="${contextPath}" class="simple-text logo-normal">
                        Library Management
                    </a>
                </div>
                <div class="sidebar-wrapper">
                    <ul class="nav">
                        <li class="nav-item" data-Link="index">
                            <a class="nav-link" href="index.jsp">
                                <i class="material-icons">dashboard</i>
                                <p>Dashboard</p>
                            </a>
                        </li>
                        <li class="nav-item " data-Link="allBook">
                            <a class="nav-link" href="allBook.jsp">
                                <i class="material-icons">content_paste</i>
                                <p>All Book</p>
                            </a>
                        </li>
                        <li class="nav-item " data-Link="borrowedBook">
                            <a class="nav-link" href="borrowedBook.jsp">
                                <i class="material-icons">library_books</i>
                                <p>Borrowed Book</p>
                            </a>
                        </li>
                        <li class="nav-item " data-Link="requestedBook">
                            <a class="nav-link" href="requestedBook.jsp">
                                <i class="material-icons">bubble_chart</i>
                                <p>Requested Book</p>
                            </a>
                        </li>
                        <li class="nav-item " data-Link="returnedBook">
                            <a class="nav-link" href="returnedBook.jsp">
                                <i class="material-icons">book</i>
                                <p>Returned Book</p>
                            </a>
                        </li>

                        <li class="nav-item" data-Link="allpublisher1">
                            <a class="nav-link" href="allpublisher1.jsp">
                                <i class="material-icons">list</i>
                                <p>All Publisher</p>
                            </a>
                        </li>
                        <li class="nav-item" data-Link="allAuthor">
                            <a class="nav-link" href="allAuthor.jsp">
                                <i class="material-icons">list</i>
                                <p>All Author</p>
                            </a>
                        </li>
                        <li class="nav-item" data-Link="allUser">
                            <a class="nav-link" href="allUser.jsp">
                                <i class="material-icons">list</i>
                                <p>All User</p>
                            </a>
                        </li>
<!--                        <li class="nav-item" data-Link="userProfile">
                            <a class="nav-link" href="userProfile.jsp">
                                <i class="material-icons">person</i>
                                <p>User Profile</p>
                            </a>
                        </li>-->
                        <c:if test="${not empty sessionScope.Role}">
                            <li class="nav-item ">
                                <a class="nav-link" href="${contextPath}/Logout">
                                    <i class="material-icons">logout</i>
                                    <p>Logout</p>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </div>
            </div>
        </c:if>
        <c:if test="${sessionScope.Role=='ta'}">
            <div class="sidebar" 
                 data-color="purple" 
                 data-background-color="black" 
                 data-image="${contextPath}/Assets/plugins/new-material-theme/img/sidebar-2.jpg">
                <!--
                  Tip 1: You can change the color of the sidebar using: data-color="purple | azure | green | orange | danger"
          
                  Tip 2: you can also add an image using data-image tag
                -->
                <div class="logo"><a href="${contextPath}" class="simple-text logo-normal">
                        Library Management
                    </a>
                </div>
                <div class="sidebar-wrapper">
                    <ul class="nav">
                        <li class="nav-item" data-Link="index">
                            <a class="nav-link" href="index.jsp">
                                <i class="material-icons">dashboard</i>
                                <p>Dashboard</p>
                            </a>
                        </li>
                        <li class="nav-item " data-Link="allBook">
                            <a class="nav-link" href="allBook.jsp">
                                <i class="material-icons">content_paste</i>
                                <p>All Book</p>
                            </a>
                        </li>
                        <li class="nav-item " data-Link="borrowedBook">
                            <a class="nav-link" href="borrowedBook.jsp">
                                <i class="material-icons">library_books</i>
                                <p>Borrowed Book</p>
                            </a>
                        </li>
                        <li class="nav-item " data-Link="requestedBook">
                            <a class="nav-link" href="requestedBook.jsp">
                                <i class="material-icons">bubble_chart</i>
                                <p>Requested Book</p>
                            </a>
                        </li>
                        <li class="nav-item " data-Link="returnedBook">
                            <a class="nav-link" href="returnedBook.jsp">
                                <i class="material-icons">book</i>
                                <p>Returned Book</p>
                            </a>
                        </li>

                        <li class="nav-item" data-Link="userProfile">
                            <a class="nav-link" href="userProfile.jsp">
                                <i class="material-icons">person</i>
                                <p>User Profile</p>
                            </a>
                        </li>
                        <c:if test="${not empty sessionScope.Role}">
                            <li class="nav-item ">
                                <a class="nav-link" href="${contextPath}/Logout">
                                    <i class="material-icons">logout</i>
                                    <p>Logout</p>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </div>
            </div>
        </c:if>
    </div>
</div>