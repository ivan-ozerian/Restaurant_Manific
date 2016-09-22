<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Restaurant Website and Gallery Template with jQuery and Google Maps</title>
		<meta charset=utf-8>
        <meta name="description" content="Restaurant Website and Gallery Template with jQuery and Google Maps" />
        <meta name="keywords" content="jquery, gallery, images, css3, html5, photography, website, template, google maps, sliding, background"/>
		<link rel="shortcut icon" href="../favicon.ico" type="image/x-icon"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style_main.css" />
		<link href='http://fonts.googleapis.com/css?family=PT+Sans+Narrow' rel='stylesheet' type='text/css' />
		<link href='http://fonts.googleapis.com/css?family=Terminal+Dosis+Light' rel='stylesheet' type='text/css'>
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
		<script src="http://maps.google.com/maps/api/js?sensor=true" type="text/javascript"></script>
    </head>
    <body>
		<div id="map"></div>
		<div id="bf_container" class="bf_container">
			<div id="bf_background" class="bf_background">
				<img src="${pageContext.request.contextPath}/resources/images/background/default1.jpg" alt="image1" style="display:none;"/>
				<div class="bf_overlay"></div>
			</div>
			<div id="bf_page_menu" class="bf_menu" >
				<h1 class="title">Manific<span>really the best pizza</span></h1>
				<ul>
					<li><a href="#" data-content="home"><span class="bf_hover"></span><span>Welcome</span></a></li>
					<li><a href="#" data-content="about"><span class="bf_hover"></span><span>About us</span></a></li>
					<li><a href="#" data-content="menu"><span class="bf_hover"></span><span>Our Menu</span></a></li>
					<li><a href="#" data-content="scheme"><span class="bf_hover"></span><span>Restaurant</span></a></li>
					<li><a href="#" data-content="staff"><span class="bf_hover"></span><span>Our staff</span></a></li>
					<li><a href="#" data-content="visit"><span class="bf_hover"></span><span>Visit us</span></a></li>
				</ul>
			</div>
			<div class="bf_page" id="home" style="display:block;">
				<div class="bf_content_text">
					<h2>Welcome</h2>
					<p>Restaurant "Manific" and it's the best pizza in Kyiv invites you to visit us as soon as possible!</p>
					<p>Our adress: Kyiv, Khreshatyk Street, 36</p>
					<p>Restautant reservations on: <br> +38(097) 111-11-11</p>
				</div>
			</div>
			<div class="bf_page" id="about">
				<div class="bf_content_text">
					<h2>About us</h2>
					<p>If you want to have a good time, please welcome! The best pizza, the best atmosphere!</p>
					<p>We was founded in 2015. We have an italian chef Gabriele Vespucchi, who knows all about a great pizza! </p>
					<p>We are sure that you will find your new favourite dish at our restaurant!</p>
				</div>
			</div>

            <div class="bf_page" id="staff">
                <div class="bf_content_text_staff">
                    <h2>Our staff</h2>
                    <p>Browse our employees.</p>
                    <div class="staff_img">
                        <img src="${pageContext.request.contextPath}/resources/images/staff/foreground/1.jpg" alt="thumb1">
                        <h3>Ivan Ozerian</h3>
                        <p>Restaurant's owner</p>
                    </div>
                    <div class="staff_img">
                        <img src="${pageContext.request.contextPath}/resources/images/staff/foreground/2.jpg" alt="thumb1">
                        <h3>Victor Barinov</h3>
                        <p>Chef</p>
                    </div>
                    <div class="staff_img">
                        <img src="${pageContext.request.contextPath}/resources/images/staff/foreground/3.jpg" alt="thumb1">
                        <h3>Peter Griffin</h3>
                        <p>Manager</p>
                    </div>
                    <div class="staff_img">
                        <img src="${pageContext.request.contextPath}/resources/images/staff/foreground/4.jpg" alt="thumb1">
                        <h3>Barbara Santa</h3>
                        <p>Waitress</p>
                    </div>
                     <div class="staff_img">
                        <img src="${pageContext.request.contextPath}/resources/images/staff/foreground/5.jpg" alt="thumb1">
                        <h3>Larry King</h3>
                        <p>Waiter</p>
                    </div>
                </div>
            </div>

             <div class="bf_page" id="scheme">
                <div class="bf_content_text_scheme">
                    <h2>Restaurant's scheme</h2>
                    <div class="scheme_img">
                        <img src="${pageContext.request.contextPath}/resources/images/scheme/scheme.jpg" alt="thumb1">
                        <p>Tables scheme</p>
                    </div>
                </div>
            </div>
           
			<div class="bf_page" id="menu">
				<div class="bf_content_text">
					<h2>Our Menu</h2>
					<p>Browse our dishes from the menu.</p>
					<ul id="bf_dishes">
						<li><a href="#"><img src="${pageContext.request.contextPath}/resources/images/thumbs/1.jpg" alt="thumb1"/></a></li>
						<li><a href="#"><img src="${pageContext.request.contextPath}/resources/images/thumbs/2.jpg" alt="thumb2"/></a></li>
						<li><a href="#"><img src="${pageContext.request.contextPath}/resources/images/thumbs/3.jpg" alt="thumb3"/></a></li>
						<li><a href="#"><img src="${pageContext.request.contextPath}/resources/images/thumbs/4.jpg" alt="thumb4"/></a></li>
						<li><a href="#"><img src="${pageContext.request.contextPath}/resources/images/thumbs/5.jpg" alt="thumb5"/></a></li>
						<li><a href="#"><img src="${pageContext.request.contextPath}/resources/images/thumbs/6.jpg" alt="thumb6"/></a></li>
					</ul>
				</div>
				<div id="bf_gallery" class="bf_gallery">
					<a id="bf_close" href="#" class="bf_close"></a>
					<div class="bf_nav">
						<a id="bf_prev" href="#" class="bf_prev"></a>
						<a id="bf_next" href="#" class="bf_next"></a>
					</div>
					<div class="bf_gallery_wrapper">
						<div class="bf_gallery_item">
							<div class="bf_heading"><h2>Pizza Rustica</h2></div>
							<div class="bf_desc">
								<p>Fresh ingredients and authentic flavours</p>
							</div>
							<img src="${pageContext.request.contextPath}/resources/images/foreground/1.jpg" alt="image1" data-bgimg="${pageContext.request.contextPath}/resources/images/background/1.jpg" />
						</div>
						<div class="bf_gallery_item">
							<div class="bf_heading"><h2>Pizza Quattro Stagioni</h2></div>
							<div class="bf_desc">
								<p>Originality meets tradition</p>
							</div>
							<img src="${pageContext.request.contextPath}/resources/images/foreground/2.jpg" alt="image1" data-bgimg="${pageContext.request.contextPath}/resources/images/background/2.jpg" />
						</div>
						<div class="bf_gallery_item">
							<div class="bf_heading"><h2>Rucolini</h2></div>
							<div class="bf_desc">
								<p>Dive into the balance of taste</p>
							</div>
							<img src="${pageContext.request.contextPath}/resources/images/foreground/3.jpg" alt="image1" data-bgimg="${pageContext.request.contextPath}/resources/images/background/3.jpg" />
						</div>
						<div class="bf_gallery_item">
							<div class="bf_heading"><h2>Salsicce Boscaiola</h2></div>
							<div class="bf_desc">
								<p>The right intensity</p>
							</div>
							<img src="${pageContext.request.contextPath}/resources/images/foreground/4.jpg" alt="image1" data-bgimg="${pageContext.request.contextPath}/resources/images/background/4.jpg" />
						</div>
						<div class="bf_gallery_item">
							<div class="bf_heading"><h2>Tortelloni Alla Zucca</h2></div>
							<div class="bf_desc">
								<p>Incredibly tasty perfection</p>
							</div>
							<img src="${pageContext.request.contextPath}/resources/images/foreground/5.jpg" alt="image1" data-bgimg="${pageContext.request.contextPath}/resources/images/background/5.jpg" />
						</div>
						<div class="bf_gallery_item">
							<div class="bf_heading"><h2>Calamaretti Fritti</h2></div>
							<div class="bf_desc">
								<p>Combine chunky and soft</p>
							</div>
							<img src="${pageContext.request.contextPath}/resources/images/foreground/6.jpg" alt="image1" data-bgimg="${pageContext.request.contextPath}/resources/images/background/6.jpg" />
						</div>
					</div>
				</div>
			</div>

			<div class="bf_page" id="visit">
				
			</div>
		</div>
		<div class="bf_footer">
			<a href="${pageContext.request.contextPath}/greetingPage"><strong>Enter for administration</strong></a>
		</div>
		<!-- The JavaScript -->
		<!-- the mousewheel plugin - optional to provide mousewheel support -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.mousewheel.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.template.min.js"></script>
    </body>
</html>