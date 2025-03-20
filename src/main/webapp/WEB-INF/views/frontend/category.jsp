<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${title }</title>
    <jsp:include page="/WEB-INF/views/frontend/layout/css.jsp"></jsp:include>
</head>

<body>
    <div class="wrapper">
        <jsp:include page="/WEB-INF/views/frontend/layout/header.jsp"></jsp:include>
        <main class="main">
            <div class="main__breadcrumb">
                <div class="container">
                    <div class="bread-crumb">
                        <span><a href="${root }/index">Trang chủ</a></span>
                        <span><a href="${root }/category">Danh mục sản phẩm</a></span>
                    </div>
                </div>
            </div>
            <div class="main__section">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-3 none block">
                            <div class="section__sidebar-widget">
                                <div class="widget__inner">
                                    <div class="widget__list">
                                        <h3 class="widget__title">Search</h3>
                                        <div class="widget__search-box">
                                            <input type="text" placeholder="Tìm kiếm..." class="widget__input">
                                            <button class="search-icon">
                                                <i class='bx bx-search'></i>
                                            </button>
                                        </div>
                                    </div>
                                    <div class="widget__list">
                                        <h3 class="widget__title">Danh mục</h3>
                                        <div class="widget__list-body">
                                            <ul class="sidebar-list">
                                                <li><a href="#">Tất cả sảm phẩm</a></li>
                                                <li><a href="#">Chó Alaska Malamute (29)</a></li>
                                                <li><a href="#">Chó Corgi (18)</a></li>
                                                <li><a href="#">Chó Poodle (20)</a></li>
                                                <li><a href="#">Mèo Anh (Dài + Ngắn) (3)</a></li>
                                                <li><a href="#">Sản phẩm mới (8)</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="widget__list">
                                        <h3 class="widget__title">Tags</h3>
                                        <div class="widget__list-body">
                                            <ul class="tags">
                                                <li><a href="#">Dogs</a></li>
                                                <li><a href="#">Cats</a></li>
                                                <li><a href="#">Alaska</a></li>
                                                <li><a href="#">Thức ăn</a></li>
                                                <li><a href="#">Hubby</a></li>
                                                <li><a href="#">Animals</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-9 col-12">
                            <div class="toolbar__wrapper">
                                <div class="toolbar__left">
                                    <div class="tool__left-btn">
                                        <i class='bx bxs-grid active-btn'></i>
                                        <i class='bx bx-menu'></i>
                                    </div>
                                </div>
                                <div class="toolbar__right">
                                    <h4 class="tool__right-title">Sắp xếp:</h4>
                                    <div class="toolbar__right-shortby">
                                        <select class="short-by">
                                            <option selected>Theo độ phổ biến</option>
                                            <option>Theo giá: Thấp đến cao</option>
                                            <option>Theo giá: Cao đến thấp</option>
                                            <option>Theo sản phẩm mới</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="row section__shop-wrapper">
                                <div class="col-12 col-lg-4 col-md-6">
                                    <div class="product">
                                        <div class="thumb">
                                            <a href="./sanpham.html" class="image">
                                                <img src="./img/products/1.jpg" class="fit-img zoom-img">
                                            </a>
                                            <span class="badges">
                                                <span class="sale">-20%</span>
                                                <span class="new">new</span>
                                            </span>
                                        </div>
                                        <div class="content">
                                            <a href="./sanpham.html" class="content-link">
                                                <h5 class="title">Chó mèo</h5>
                                            </a>
                                            <span class="price">
                                                <span class="old">20.000.000đ</span>
                                                <span class="new">15.000.000đ</span>
                                            </span>
                                            <span class="symbol">
                                                <a href="#"><i class='bx bx-heart'></i></a>
                                                <a href="#"><i class='bx bx-cart'></i></a>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12 col-lg-4 col-md-6">
                                    <div class="product">
                                        <div class="thumb">
                                            <a href="./sanpham.html" class="image">
                                                <img src="./img/products/6.jpg" class="fit-img zoom-img">
                                            </a>
                                            <span class="badges">
                                                <span class="sale">-20%</span>
                                            </span>
                                        </div>
                                        <div class="content">
                                            <a href="./sanpham.html" class="content-link">
                                                <h5 class="title">Chó mèo</h5>
                                            </a>
                                            <span class="price">
                                                <span class="old">20.000.000đ</span>
                                                <span class="new">15.000.000đ</span>
                                            </span>
                                            <span class="symbol">
                                                <a href="#"><i class='bx bx-heart'></i></a>
                                                <a href="#"><i class='bx bx-cart'></i></a>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12 col-lg-4 col-md-6">
                                    <div class="product">
                                        <div class="thumb">
                                            <a href="./sanpham.html" class="image">
                                                <img src="./img/products/4.jpeg" class="fit-img zoom-img">
                                            </a>
                                            <span class="badges">
                                                <!-- <span class="sale">-20%</span> -->
                                            </span>
                                        </div>
                                        <div class="content">
                                            <a href="./sanpham.html" class="content-link">
                                                <h5 class="title">Chó mèo</h5>
                                            </a>
                                            <span class="price">
                                                <!-- <span class="old">20.000.000đ</span> -->
                                                <span class="new">15.000.000đ</span>
                                            </span>
                                            <span class="symbol">
                                                <a href="#"><i class='bx bx-heart'></i></a>
                                                <a href="#"><i class='bx bx-cart'></i></a>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12 col-lg-4 col-md-6">
                                    <div class="product">
                                        <div class="thumb">
                                            <a href="./sanpham.html" class="image">
                                                <img src="./img/products/8.jpg" class="fit-img zoom-img">
                                            </a>
                                            <span class="badges">
                                                <span class="sale">-10%</span>
                                            </span>
                                        </div>
                                        <div class="content">
                                            <a href="./sanpham.html" class="content-link">
                                                <h5 class="title">Chó mèo</h5>
                                            </a>
                                            <span class="price">
                                                <span class="old">20.000.000đ</span>
                                                <span class="new">15.000.000đ</span>
                                            </span>
                                            <span class="symbol">
                                                <a href="#"><i class='bx bx-heart'></i></a>
                                                <a href="#"><i class='bx bx-cart'></i></a>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12 col-lg-4 col-md-6">
                                    <div class="product">
                                        <div class="thumb">
                                            <a href="./sanpham.html" class="image">
                                                <img src="./img/products/3.jpeg" class="fit-img zoom-img">
                                            </a>
                                            <span class="badges">
                                                <!-- <span class="sale">-20%</span> -->

                                            </span>
                                        </div>
                                        <div class="content">
                                            <a href="./sanpham.html" class="content-link">
                                                <h5 class="title">Chó mèo</h5>
                                            </a>
                                            <span class="price">
                                                <!-- <span class="old">20.000.000đ</span> -->
                                                <span class="new">15.000.000đ</span>
                                            </span>
                                            <span class="symbol">
                                                <a href="#"><i class='bx bx-heart'></i></a>
                                                <a href="#"><i class='bx bx-cart'></i></a>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12 col-lg-4 col-md-6">
                                    <div class="product">
                                        <div class="thumb">
                                            <a href="./sanpham.html" class="image">
                                                <img src="./img/products/7.jpeg" class="fit-img zoom-img">
                                            </a>
                                            <span class="badges">
                                                <span class="sale">-20%</span>
                                            </span>
                                        </div>
                                        <div class="content">
                                            <a href="./sanpham.html" class="content-link">
                                                <h5 class="title">Chó mèo</h5>
                                            </a>
                                            <span class="price">
                                                <span class="old">20.000.000đ</span>
                                                <span class="new">15.000.000đ</span>
                                            </span>
                                            <span class="symbol">
                                                <a href="#"><i class='bx bx-heart'></i></a>
                                                <a href="#"><i class='bx bx-cart'></i></a>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12 col-lg-4 col-md-6">
                                    <div class="product">
                                        <div class="thumb">
                                            <a href="./sanpham.html" class="image">
                                                <img src="./img/products/8.jpg" class="fit-img zoom-img">
                                            </a>
                                            <span class="badges">
                                                <span class="sale">-10%</span>
                                            </span>
                                        </div>
                                        <div class="content">
                                            <a href="./sanpham.html" class="content-link">
                                                <h5 class="title">Chó mèo</h5>
                                            </a>
                                            <span class="price">
                                                <span class="old">20.000.000đ</span>
                                                <span class="new">15.000.000đ</span>
                                            </span>
                                            <span class="symbol">
                                                <a href="#"><i class='bx bx-heart'></i></a>
                                                <a href="#"><i class='bx bx-cart'></i></a>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12 col-lg-4 col-md-6">
                                    <div class="product">
                                        <div class="thumb">
                                            <a href="./sanpham.html" class="image">
                                                <img src="./img/products/3.jpeg" class="fit-img zoom-img">
                                            </a>
                                            <span class="badges">
                                                <!-- <span class="sale">-20%</span> -->
                                            </span>
                                        </div>
                                        <div class="content">
                                            <a href="./sanpham.html" class="content-link">
                                                <h5 class="title">Chó mèo</h5>
                                            </a>
                                            <span class="price">
                                                <!-- <span class="old">20.000.000đ</span> -->
                                                <span class="new">15.000.000đ</span>
                                            </span>
                                            <span class="symbol">
                                                <a href="#"><i class='bx bx-heart'></i></a>
                                                <a href="#"><i class='bx bx-cart'></i></a>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12 col-lg-4 col-md-6">
                                    <div class="product">
                                        <div class="thumb">
                                            <a href="./sanpham.html" class="image">
                                                <img src="./img/products/7.jpeg" class="fit-img zoom-img">
                                            </a>
                                            <span class="badges">
                                                <span class="sale">-20%</span>
                                            </span>
                                        </div>
                                        <div class="content">
                                            <a href="./sanpham.html" class="content-link">
                                                <h5 class="title">Chó mèo</h5>
                                            </a>
                                            <span class="price">
                                                <span class="old">20.000.000đ</span>
                                                <span class="new">15.000.000đ</span>
                                            </span>
                                            <span class="symbol">
                                                <a href="#"><i class='bx bx-heart'></i></a>
                                                <a href="#"><i class='bx bx-cart'></i></a>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="section__shop-toolbars">
                                <div class="shop__toolbar">
                                    <ul class="pagination">
                                        <li class="pages-item active"><a href="#" class="pages-link">1</a></li>
                                        <li class="pages-item"><a href="#" class="pages-link">2</a></li>
                                        <li class="pages-item"><a href="#" class="pages-link">3</a></li>
                                        <li class="pages-item"><a href="#" class="pages-link"><i
                                                    class='bx bx-chevrons-right'></i></a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <jsp:include page="/WEB-INF/views/frontend/layout/footer.jsp"></jsp:include>
        <div class="scroll__top">
            <i class='bx bx-up-arrow-alt'></i>
        </div>
        
    </div>
    <jsp:include page="/WEB-INF/views/frontend/layout/js.jsp"></jsp:include>

</body>

</html>