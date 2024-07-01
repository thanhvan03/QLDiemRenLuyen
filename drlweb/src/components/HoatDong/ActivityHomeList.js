    import React, { useContext, useEffect, useState } from "react";
    import { Spinner, Button, Container, Card} from "react-bootstrap";
    import { Link, useNavigate } from "react-router-dom";
    import APIs, { authApi, endpoints } from "../../configs/APIs";
    import { MyDispatchContext, MyUserContext } from "../../configs/Context";
    import Carousel from 'react-bootstrap/Carousel';  
    import { Bar } from 'react-chartjs-2';
    import { Chart, CategoryScale, LinearScale, BarElement, Title } from 'chart.js';

    Chart.register(CategoryScale, LinearScale, BarElement, Title);

    const ActivityHomeList = () => {
        const [activities, setActivities] = useState(null);
        const [totalPages, setTotalPages] = useState(0);
        const [currentPage, setCurrentPage] = useState(0);
        const pageSize = 5; 
        const [numLike, setnumLike] = useState(0);
        const [isLike, setIsLike] = useState({});
        const [shouldReloadActivities, setShouldReloadActivities] = useState(false);
        const nav = useNavigate();
        const user = useContext(MyUserContext);
        const dispatch = useContext(MyDispatchContext);

        ///////////////////////////////////////////////
        ////Hoc Ky
        const [semesterData, setSemesterData] = useState([]);
        const [semester, setSemester] = useState([]);

        useEffect(() => {
            const fetchData = async () => {
              try {
                // Fetch danh sách học kỳ mà sinh viên đã tham gia
                const hocKyResponse = await authApi().get(`http://localhost:8080/QLDRL/api/hockydathamgia/`);
                const hocKyList = hocKyResponse.data;
        
                setSemester(hocKyList);
        
                // Fetch thông tin thành tích cho từng học kỳ
                const dataPromises = hocKyList.map(async hocKy => {
                  const thanhTichResponse = await authApi().get(`http://localhost:8080/QLDRL/api/sinhvien/thanhtich/${hocKy.id}`);
                  return thanhTichResponse.data;
                });
        
                // Chờ tất cả các promise hoàn thành và cập nhật state
                const allData = await Promise.all(dataPromises);
                setSemesterData(allData);
                console.log('Fetched data:', allData);
              } catch (error) {
                console.error('Error fetching data:', error);
              }
            };
        
            fetchData();
          }, []);

        // Biểu đồ dữ liệu
        const chartData = {
            labels: semester.map(data => `${data.hocky} - ${data.namhoc}`),
            datasets: [{
            label: 'Tổng điểm',
            data: semesterData.map(data => data.tongDiem),
            backgroundColor: 'rgba(54, 162, 235, 0.6)',
            borderColor: 'rgba(54, 162, 235, 1)',
            borderWidth: 1
            }]
        };

        // Tùy chọn của biểu đồ
        const chartOptions = {
            responsive: true,
            maintainAspectRatio: false,
            scales: {
            y: {
                beginAtZero: true
            }
            }
        };
        ///////////////////////////////////////////////////

        const formatDate = (timestamp) => {
            const date = new Date(timestamp);
            const day = date.getDate().toString().padStart(2, '0');
            const month = (date.getMonth() + 1).toString().padStart(2, '0');
            const year = date.getFullYear();
            return `${day}/${month}/${year}`;
        }

        ////////////////////////////////////////////////
        /////Like
        // ///Kiểm tra User đã like hoạt động đó chưa
        const checkUserLike = async (activityId) => {
            try {
                const api = authApi();
                const response = await api.get(endpoints.checklike(activityId));
                if (response.status === 200 && response.data) {
                    // Nếu API trả về dữ liệu, người dùng đã thích hoạt động
                    setIsLike(prevIsLike => ({
                        ...prevIsLike,
                        [activityId]: true
                    }));
                } else {
                    setIsLike(prevIsLike => ({
                        ...prevIsLike,
                        [activityId]: false
                    }));
                }      
            } catch(error) {
                console.error('Đã xảy ra lỗi khi thực hiện thích hoạt động:', error);
            }
        };   

        //Like Hoat Dong
        const handleLike = async (activityId) => {
            try {
                const api = authApi();
                const formData = new FormData();
                formData.append('idHoatDong', activityId);

                const response = await api.post(endpoints.like, formData, {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }
                });
                console.log("Like thành công:", response.data);
                setIsLike(prevIsLike => ({
                    ...prevIsLike,
                    [activityId]: true
                }));
                setShouldReloadActivities(true);
            }  catch (error) {
                if (error.response) {
                    const statusCode = error.response.status;
                    const errorMessage = error.response.data;
                    
                    // Xử lý các mã lỗi và thông báo lỗi từ server
                    if (statusCode === 400) {
                            alert(`Không thể thích hành động này: ${errorMessage}`);                   
                    } else {
                        alert(`Đã xảy ra lỗi khi thực hiện thích hoạt động. Mã lỗi: ${statusCode}`);
                    }
                } else {
                    console.error('Đã xảy ra lỗi khi thực hiện thích hoạt động:', error);
                    alert('Bạn chưa đăng nhập!');
                }
            }
        };

        ///Hủy Like
        const handleCancelLike = async (activityId) => {
            try {
                const api = authApi();
                const response = await api.delete(`${endpoints.removeLike}?idHoatDong=${activityId}`);
                if (response.status < 200 || response.status >= 300) {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }
                console.log("Xóa Like thành công:", response.data);
                setIsLike(prevIsLike => ({
                    ...prevIsLike,
                    [activityId]: false
                }));
                setShouldReloadActivities(true);
            } catch(error) {
                console.error('Đã xảy ra lỗi khi thực hiện thích hoạt động:', error);
                alert('Bạn chưa đăng nhập!');
            }
        }

        ///Tong so luot like hoat dong
        const totalLike = async (activityId) => {
            try {
                const response = await APIs.get(endpoints.countlike(activityId));
                if (response.status < 200 || response.status >= 300) {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }
                const data = response.data;
                // Cập nhật state với số lượng like cho từng hoạt động
                setnumLike(prevLikes => ({ ...prevLikes, [activityId]: data })); 
            } catch (error) {
                console.error("Error loading activities:", error);
            }
        }
        //////////////////////////////////////////////////////////////

        /////////////////////////////////////////////////////////////
        ///Hoạt động
        ////Đăng ký hoạt động
        const handleRegistration = async (activityId) => {
            try {
                const api = authApi(); // Lấy instance axios từ hàm authApi
                const formData = new FormData();
                formData.append('idHoatDong', activityId);
        
                const response = await api.post(endpoints.registry, formData, {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }
                });
        
                console.log('Đăng ký thành công:', response.data);
                // Tải lại danh sách hoạt động sau khi đăng ký thành công
                loadActivities();
        
                alert('Đăng ký thành công!');
            } catch (error) {
                if (error.response) {
                    // Lấy mã lỗi và thông báo lỗi từ response của server
                    const statusCode = error.response.status;
                    const errorMessage = error.response.data;
        
                    if (statusCode === 400) {
                        // Kiểm tra thông báo lỗi cụ thể từ backend
                        if (errorMessage === "Thời gian đăng ký hoạt động không hợp lệ.") {
                            alert("Đăng ký thất bại: Thời gian đăng ký hoạt động không hợp lệ.");
                        } else if (errorMessage === "Sinh Vien Da Dang Ky") {
                            alert("Đăng ký thất bại: Sinh viên đã đăng ký hoạt động này.");
                        } else {
                            alert(`Đăng ký thất bại: ${errorMessage}`);
                        }
                    } else {
                        alert(`Đã xảy ra lỗi khi đăng ký. Mã lỗi: ${statusCode}`);
                    }
                } else {
                    console.error('Đã xảy ra lỗi khi đăng ký:', error);
                    alert('Đã xảy ra lỗi khi đăng ký. Vui lòng thử lại sau.');
                }        
            }
        };

        // Hàm get Hoat Dong
        const loadActivities = async (page) => {
            try {
                const response = await APIs.get(`${endpoints.activities}?page=${page}`);
                if (response.status < 200 || response.status >= 300) {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }
                const data = response.data;
                setActivities(data);
                setCurrentPage(page);            
                data.forEach(async (activity) => {
                    await totalLike(activity.id); 
                    if (user !== null) {
                        // Nếu người dùng đã đăng nhập, kiểm tra trạng thái like của hoạt động
                        await checkUserLike(activity.id);
                    }
                });
                
            } catch (error) {
                console.error("Error loading activities:", error);
            }
        };

        // Tinh tong so luong trang
        const calculateTotalPages = async () => {
            try {
                const response = await APIs.get(endpoints.totalactive);
                if (response.status < 200 || response.status >= 300) {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }
                const totalCount = response.data.length; // Assuming API returns array length
                const pages = Math.ceil(totalCount / pageSize);
                setTotalPages(pages);
            } catch (error) {
                console.error("Error calculating total pages:", error);
            }
        };

        // Xu ly Click vao chuyen trang
        const handlePaginationClick = (page) => {
            loadActivities(page);
        };

        ///Fetch API
        useEffect(() => {
            if (activities === null || shouldReloadActivities) {
                loadActivities(currentPage);
                calculateTotalPages();
                setShouldReloadActivities(false);
            }
        }, [shouldReloadActivities, activities, currentPage]);


        // Xử Lý Phân Trang
        const renderPagination = () => {
            if (totalPages < 1) {
                return null; 
            }

            const pages = [...Array(totalPages).keys()]; 

            return (
                <nav aria-label="Page navigation example" style={{ width: '87%', margin: 'auto' }}>
                    <ul className="pagination">
                        <li className={`page-item ${currentPage === 0 ? 'disabled' : ''}`}>
                            <Button className="page-link" onClick={() => handlePaginationClick(currentPage - 1)} aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                                <span className="sr-only">Previous</span>
                            </Button>
                        </li>
                        {pages.map(page => (
                            <li key={page} className={`page-item ${currentPage === page ? 'active' : ''}`}>
                                <Button className="page-link" onClick={() => handlePaginationClick(page)}>
                                    {page + 1}
                                </Button>
                            </li>
                        ))}
                        <li className={`page-item ${currentPage === totalPages - 1 ? 'disabled' : ''}`}>
                            <Button className="page-link" onClick={() => handlePaginationClick(currentPage + 1)} aria-label="Next">                         
                                <span className="sr-only">Next</span>
                                <span aria-hidden="true">&raquo;</span>
                            </Button>
                        </li>
                    </ul>
                </nav>
            );
        };

        ///Xử Lý Like
        const likeAction = (activityId) => {  
            const liked = isLike[activityId]; 

            if (liked){           
                return (
                    <Button className="bi bi-hand-thumbs-up-fill " style={{ marginLeft: '10px' }} onClick={() => handleCancelLike(activityId)}>
                        {numLike[activityId] || 0}  
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
                            <path d="M6.956 1.745C7.021.81 7.908.087 8.864.325l.261.066c.463.116.874.456 1.012.965.22.816.533 2.511.062 4.51a10 10 0 0 1 .443-.051c.713-.065 1.669-.072 2.516.21.518.173.994.681 1.2 1.273.184.532.16 1.162-.234 1.733q.086.18.138.363c.077.27.113.567.113.856s-.036.586-.113.856c-.039.135-.09.273-.16.404.169.387.107.819-.003 1.148a3.2 3.2 0 0 1-.488.901c.054.152.076.312.076.465 0 .305-.089.625-.253.912C13.1 15.522 12.437 16 11.5 16H8c-.605 0-1.07-.081-1.466-.218a4.8 4.8 0 0 1-.97-.484l-.048-.03c-.504-.307-.999-.609-2.068-.722C2.682 14.464 2 13.846 2 13V9c0-.85.685-1.432 1.357-1.615.849-.232 1.574-.787 2.132-1.41.56-.627.914-1.28 1.039-1.639.199-.575.356-1.539.428-2.59z"/>
                        </svg>
                    </Button>     
                );
            }                    
            if (!liked) {
                return(
                    <Button className="bi bi-hand-thumbs-up " style={{ marginLeft: '10px', backgroundColor: "white", color: "black"}} onClick={() => handleLike(activityId)}>
                    {numLike[activityId] || 0}  
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
                        <path d="M8.864.046C7.908-.193 7.02.53 6.956 1.466c-.072 1.051-.23 2.016-.428 2.59-.125.36-.479 1.013-1.04 1.639-.557.623-1.282 1.178-2.131 1.41C2.685 7.288 2 7.87 2 8.72v4.001c0 .845.682 1.464 1.448 1.545 1.07.114 1.564.415 2.068.723l.048.03c.272.165.578.348.97.484.397.136.861.217 1.466.217h3.5c.937 0 1.599-.477 1.934-1.064a1.86 1.86 0 0 0 .254-.912c0-.152-.023-.312-.077-.464.201-.263.38-.578.488-.901.11-.33.172-.762.004-1.149.069-.13.12-.269.159-.403.077-.27.113-.568.113-.857 0-.288-.036-.585-.113-.856a2 2 0 0 0-.138-.362 1.9 1.9 0 0 0 .234-1.734c-.206-.592-.682-1.1-1.2-1.272-.847-.282-1.803-.276-2.516-.211a10 10 0 0 0-.443.05 9.4 9.4 0 0 0-.062-4.509A1.38 1.38 0 0 0 9.125.111zM11.5 14.721H8c-.51 0-.863-.069-1.14-.164-.281-.097-.506-.228-.776-.393l-.04-.024c-.555-.339-1.198-.731-2.49-.868-.333-.036-.554-.29-.554-.55V8.72c0-.254.226-.543.62-.65 1.095-.3 1.977-.996 2.614-1.708.635-.71 1.064-1.475 1.238-1.978.243-.7.407-1.768.482-2.85.025-.362.36-.594.667-.518l.262.066c.16.04.258.143.288.255a8.34 8.34 0 0 1-.145 4.725.5.5 0 0 0 .595.644l.003-.001.014-.003.058-.014a9 9 0 0 1 1.036-.157c.663-.06 1.457-.054 2.11.164.175.058.45.3.57.65.107.308.087.67-.266 1.022l-.353.353.353.354c.043.043.105.141.154.315.048.167.075.37.075.581 0 .212-.027.414-.075.582-.05.174-.111.272-.154.315l-.353.353.353.354c.047.047.109.177.005.488a2.2 2.2 0 0 1-.505.805l-.353.353.353.354c.006.005.041.05.041.17a.9.9 0 0 1-.121.416c-.165.288-.503.56-1.066.56z"/>
                    </svg>
                </Button>  
                );            
            }
        };
         
        ///Banner
        function UncontrolledExample() {
            return (
              <Carousel style={{marginLeft: "100px", marginRight: "100px", marginTop: "10px", border: "outset"}}>
                <Carousel.Item>
                    <img style={{height: "300px"}}
                        className="d-block w-100"
                        src="https://csnh.vhu.edu.vn/Temp/ArticleImage/e582e635-9499-4a41-9fc6-e6d343f6bf4e.png"
                        alt="Hình ảnh 1"
                    />     
                </Carousel.Item>
                <Carousel.Item>
                    <img style={{height: "300px"}}
                        className="d-block w-100"
                        src="https://sdh.ou.edu.vn/asset/slideshow/ss-f7ec696c05b82147106a6159b3ba0d44.png"
                        alt="Hình ảnh 2"
                    />         
                </Carousel.Item>
                <Carousel.Item>
                    <img style={{height: "300px"}}
                        className="d-block w-100"
                        src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcShYLPDVgrMMkh5pnP62VSTr0qN3eybiaIqJQ&s"
                        alt="Hình ảnh 3"
                    />  
                </Carousel.Item>
              </Carousel>
            );
          }

        return (
            <div className="feed-container" style={{ background: "#f0f0f0" }}>
                <div className="Banner">
                    {UncontrolledExample()}
                </div>
                <div className="row" id="Card">
                    <div className="col-md-4 col-12 mt-3 pe-0">
                        <div className="row pe-4" style={{paddingLeft: "70px"}}>
                            <div className="col-md-2 col-12"></div>
                            <div className="col-md-10 col-12">
                                <div style={{margin: "auto", border: "2px groove"}}>
                                    <h5 className="text-center" style={{display: "flex", justifyContent: "center", alignItems: "center", color: "white", backgroundColor: "#0a58ca", margin: "auto", height: "50px"}}>TÍNH NĂNG</h5>
                                </div>
                                <div style={{backgroundColor: "white", padding: "10px", border: "2px groove"}}>
                                    <Link to="/registered-activities" className="nav-link mt-1" style={{color: "blue", borderBottom: "1px groove #ccc"}}>
                                        <span className="ms-2">&rsaquo; Các hoạt động đã đăng ký</span>
                                    </Link>
                                    <Link to="/participated-activities" className="nav-link  mt-2" style={{color: "blue", borderBottom: "1px groove #ccc"}}>
                                        <span className="ms-2">&rsaquo; Các hoạt động đã tham gia</span>
                                    </Link>
                                    <Link to="/" className="nav-link  mt-2" style={{color: "blue", borderBottom: "1px groove #ccc"}}>
                                        <span className="ms-2">&rsaquo; Bảng điểm thành tích</span>
                                    </Link>
                                    
                                    {user === null ? ( <>
                                        <div>
                                            <Button as={Link} to="/login"  style={{width: "100%", marginTop: "10px", color: "black", backgroundColor: "#ffc107" }} >Đăng Nhập</Button>
                                        </div>
                                    </>
                                    ) : ( <>
                                        <Link to="/userinfo" className="nav-link  mt-2" style={{color: "blue", borderBottom: "1px groove #ccc"}}>
                                            <span className="ms-2">&rsaquo; Thông tin sinh viên</span>
                                        </Link>
                                        <div>
                                            <Button as={Link} to="/" onClick={() => dispatch({ type: "logout" })}  style={{width: "100%", marginTop: "10px", color: "black", backgroundColor: "#ffc107" }} >Đăng Xuất</Button>
                                        </div>
                                    </>
                                    )}                                   
                                </div>
                                {/* Vẻ biểu đồ chart-js */}
                                {user && (
                                    <div style={{marginTop: "70px"}}>
                                        <Card style={{height: "400px"}}>
                                            <Card.Header className="text-center" style={{}}>
                                                Tổng Kết Thành Tích Qua Các Kỳ
                                            </Card.Header>
                                            <Card.Body>
                                            <div style={{ height: '300px' }}>
                                                <Bar data={chartData} options={chartOptions} />
                                            </div>
                                            </Card.Body>
                                        </Card> 
                                    </div>
                                )}
                            </div>
                        </div>
                    </div>
                    <div className="col-md-8 col-12 mt-2 ps-0 pe-4" id="activitylist">
                        {activities === null ? (
                        <Spinner animation="grow" variant="primary" />
                        ) : (
                        <Container>                                                            
                            {activities.map((activity, index) => (                                                                 
                                <Card key={activity.id} className={`mb-3 mt-2 ${index % 2 === 0 ? 'bg-light' : 'bg-white'}`}  
                                    style={{ width: '85%', margin: 'auto' }}>
                                    <div className="row"> 
                                        <div className="col-md-10 col-12">
                                            <Card.Body>
                                                <Card.Title>    
                                                    <Link to={`/activities/${activity.id}`} style={{ textDecoration: 'none', color: 'inherit' }}>
                                                        {activity.tenhoatdong}
                                                    </Link>
                                                </Card.Title>
                                                <Card.Text>
                                                    <strong>Tiêu chí:</strong> {activity.tieuchi}<br />
                                                    <strong>Điểm:</strong> {activity.diem}<br />
                                                    <strong>Địa điểm:</strong> {activity.diadiem}<br />
                                                    <strong>Học kỳ:</strong> {activity.idHocKy.hocky} - {activity.idHocKy.namhoc} <br />
                                                </Card.Text>
                                                <Button variant="info" size="md" style={{ marginRight: '10px' }} onClick={() => nav(`/activities/${activity.id}`)}>Xem chi tiết</Button>
                                                <Button variant="success" size="md" onClick={() => handleRegistration(activity.id)}>Đăng ký</Button> 
                                                
                                                {likeAction(activity.id)}                                        
                                            </Card.Body>                                           
                                        </div> 
                                        <div className="col-md-2 col-12 ps-5 pt-2">
                                            <small> {formatDate(activity.ngaybatdau)} </small>
                                        </div>    
                                    </div>                                    
                                </Card>                                                                                                                                                                             
                            ))}       
                            {renderPagination()}                                                                                                 
                        </Container>                           
                        )}
                    </div>                  
                </div>                                  
            </div>
    );
} 

export default ActivityHomeList;


