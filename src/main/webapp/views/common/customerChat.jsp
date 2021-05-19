<%@include file="../inc/header.jsp" %>

<style>
    .card {
        position: relative;
        display: -webkit-box;
        display: -webkit-flex;
        display: -ms-flexbox;
        display: flex;
        -webkit-box-orient: vertical;
        -webkit-box-direction: normal;
        -webkit-flex-direction: column;
        -ms-flex-direction: column;
        flex-direction: column;
        background-color: #fff;
        border: 1px solid rgba(0, 0, 0, 0.125);
        border-radius: 0.25rem;
        padding: 10px;
    }
    @media (min-width: 576px) {
        .card-columns {
            -webkit-column-count: 3;
            -moz-column-count: 3;
            column-count: 3;
            -webkit-column-gap: 1.25rem;
            -moz-column-gap: 1.25rem;
            column-gap: 1.25rem;
        }
        .card-columns .card {
            display: inline-block;
            width: 100%;
            margin-bottom: 0.75rem;
        }
    }
    .defaultImg {
        height: 67px;
    }
    .defaultName {
        padding: 17px;
    }
    .pageMargin {
        margin-bottom: 20px;
    }
    .formHeading {
        padding: 8px;
    }
</style>

<!--<h1> Hellow Chat</h1>-->

<div class="container">


    <c:if test="${sessionScope.Role=='ta'}">
        <div class="card pageMargin">
            <!--<pre>-->
            <h3> Save Discussion</h3>
            <form method="post" action="${contextPath}/saveMailActivity" id="saveMail" class="form-horizontal">


                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-2 control-label" for="textinput">From :</label>  
                    <div class="col-md-8">
                        <input id="from" name="from" type="text" placeholder="From " class="form-control input-md" value="${sessionScope.UserName}" required="required">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-2 control-label" for="textinput">To :</label>  
                    <div class="col-md-8">
                        <input id="to" name="to" type="text" placeholder="To " class="form-control input-md" value="${sessionScope.MappedCustomerName}" required="required">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-2 control-label" for="textinput">Cc :</label>  
                    <div class="col-md-8">
                        <input id="cc" name="cc" type="text" placeholder="Cc " class="form-control input-md" >
                    </div>
                </div>


                <div class="form-group">
                    <label class="col-md-2 control-label" for="textinput">Subject :</label>  
                    <div class="col-md-8">
                        <input id="subject" name="subject" type="text" placeholder="Subject " class="form-control input-md" required="required" >
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-2 control-label" for="textinput">Date :</label>  
                    <div class="col-md-8">
                        <input id="date" name="date" type="date" placeholder="date " class="form-control input-md" required="required">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-2 control-label" for="textinput">Content :</label>  
                    <div class="col-md-8">
                        <textarea class="form-control" id="content" name="content" placeholder="Start typing discussion...." required="required"></textarea>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-2 control-label" for="button1id"> </label>
                    <div class="col-md-4">
                        <button id="saveMail" name="button1id" class="btn btn-success">Save Mail</button>
                        <!--<button id="draftMail" name="button2id" class="btn btn-danger" type="reset">Draft Mail</button>-->
                    </div>
                </div>
            </form>




            <!--</pre>-->
        </div>
    </c:if>

    <div class="card-email-thread">


    </div>
</div>
<script src="../../Assets/js/user-customerChat.js" type="text/javascript"></script>



<script id="tmpl-addProject" type="x-tmpl-mustache">

    <div class='col l3 card main-card pageMargin' data-toggle="tooltip" data-id={{tcsEmailCustomerId}}>
    <div class='card-content projectCardContent' >
    <div class="col-sm-1">
    <img src="../../Assets/image/default-profile.png" alt="" class="img-responsive img-thumbnail defaultImg"/>
    </div>
    <div class="col-sm-11 defaultName">



    <div><pre>From : {{tcsEmailFrom}}<br>To: {{tcsEmailTo}}<br>Cc: {{tcsEmailBcc}}<br>Sent: {{tcsEmailCreated}}<br>Subject: {{tcsEmailSubject}}</pre></div>
    <div><pre>{{tcsEmailContent}}</pre></div>
    </div>
    </div>
    <div class="card-action">
    </div>
    </div>
</script>

