import { useEffect, useState } from "react";
import { Card, Button, Modal, Form, Container } from "react-bootstrap";
import { useParams } from "react-router-dom";
import APIs, { authApi, endpoints } from "../../configs/APIs";
import MySpinner from "../commons/MySpinner";

const ActivityDetails = () => {
    const [activity, setActivity] = useState(null);
    const {activityId} = useParams();
    const [comments, setComments] = useState([]);
    const [newComment, setNewComment] = useState("");
    const [showComments, setShowComments] = useState(false);
    const pageSize = 8; 
    const [totalPages, setTotalPages] = useState(0);
    const [currentPage, setCurrentPage] = useState(0);

    const loadActivity = async () => {
        try {
            let res = await APIs.get(endpoints['details'](activityId));
            setActivity(res.data)
        } catch (ex) {
            console.error(ex);
        }
    }

    useEffect(() => {
        loadActivity();
    }, [activityId]);

    const formatDate = (timestamp) => {
        const date = new Date(timestamp);
        const day = date.getDate().toString().padStart(2, '0');
        const month = (date.getMonth() + 1).toString().padStart(2, '0');
        const year = date.getFullYear();
        return `${day}/${month}/${year}`;
    }

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
            loadActivity();
    
            alert('Đăng ký thành công!');
        } catch (error) {
            if (error.response) {
                // Lấy mã lỗi và thông báo lỗi từ response của server
                const statusCode = error.response.status;
                const errorMessage = error.response.data;
    
                if (statusCode === 400) {
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

    //Hiện form để bình luận 
    const showCommentForm = () => {
        setShowComments((true));
    }

    ///Đóng form bình luận
    const closeCommentForm = () => {
        setShowComments((false));
    }
    
    ///Bình Luận
    const handleSubmitComment = async () => {
        try {
            const api = authApi(); 
                const formData = new FormData();
                formData.append('idHoatDong', activityId);
                formData.append('noidung', newComment);     

                const response = await api.post(endpoints.comment, formData);
                console.log('Binh luan thanh cong:', response.data);

                setNewComment("");
                fetchComments(currentPage);
                calculateTotalPages();
        } catch (error) {
            console.error(error);
        }
    }

    ////Hiện các bình luận có phân trang
    const fetchComments = async (page) => {
        try {
            // const formData = new FormData();
            // formData.append('page', page);
            const res = await APIs.get(`${endpoints.getcomments(activityId)}?page=${page}`);
            setComments(res.data);
            setCurrentPage(page);
        } catch (ex) {
            console.error(ex);
        }
    }

    // Tinh tong so luong trang
    const calculateTotalPages = async () => {
        try {
            const response = await APIs.get(endpoints.totalcomments(activityId));
            if (response.status < 200 || response.status >= 300) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            const totalCount = response.data.length; 
            const pages = Math.ceil(totalCount / pageSize);
            setTotalPages(pages);
        } catch (error) {
            console.error("Error calculating total pages:", error);
        }
    };

    // Xu ly Click vao chuyen trang binh luan
    const handlePaginationClick = (page) => {
        fetchComments(page);
    };

    // Xử Lý Phân Trang
    const renderPagination = () => {
        if (totalPages <= 1) {
            return null; 
        }

        const pages = [...Array(totalPages).keys()]; 

        return (
            <nav aria-label="Page navigation example"  style={{ width: '100%', margin: 'auto' }}>
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

    useEffect(() => {
        fetchComments(currentPage);
        calculateTotalPages();
    }, [activityId, currentPage]);

    return (
        <Container style={{ background: "#f0f0f0" }}>
            {activity === null ? (
                <MySpinner />
            ) : (
                <div className="ps-5 pe-5">
                    <h1 className="text-center text-primary mt-3 mb-3 pt-2">
                        {activity.tenhoatdong}
                    </h1>
                    <Card className="mb-3">
                        <Card.Body>
                            <Card.Text>
                                <p>
                                    <strong>Nội dung:</strong>{" "}
                                    {activity.noidung}
                                </p>
                                <p>
                                    <strong>Tiêu chí:</strong>{" "}
                                    {activity.tieuchi}
                                </p>
                                <p>
                                    <strong>Điểm:</strong> {activity.diem}
                                </p>
                                <p>
                                    <strong>Ngày bắt đầu:</strong>{" "}
                                    {formatDate(activity.ngaybatdau)}
                                </p>
                                <p>
                                    <strong>Ngày kết thúc:</strong>{" "}
                                    {formatDate(activity.ngayketthuc)}
                                </p>
                                <p>
                                    <strong>Địa điểm:</strong>{" "}
                                    {activity.diadiem}
                                </p>
                                <p>
                                    <strong>Học kỳ:</strong>{" "}
                                    {activity.idHocKy.hocky} -{" "}
                                    {activity.idHocKy.namhoc}
                                </p>
                            </Card.Text>
                            <Button variant="secondary" size="md" style={{ marginRight: '10px' }} onClick={showCommentForm}>Bình luận</Button>
                            <Button variant="success" size="md" onClick={() => handleRegistration(activity.id)}> Đăng ký</Button>
                            {showComments === true && (
                                <Container>
                                    <h3 className="mt-3">Bình luận</h3>
                                    
                                    <Form>
                                        <Form.Group>
                                            <Form.Control
                                                as="textarea"
                                                rows={3}
                                                placeholder="Viết bình luận..."
                                                value={newComment}
                                                onChange={(e) =>
                                                    setNewComment(e.target.value)
                                                }
                                            />
                                        </Form.Group>
                                        <Button
                                            className="mt-3"
                                            variant="primary"
                                            onClick={handleSubmitComment}
                                        >
                                           Gửi
                                        </Button>
                                        <Button className=" mt-3 ms-2 text-center btn btn-danger"  onClick={closeCommentForm}> Đóng </Button>
                                    </Form>
                                </Container>
                            )}                      
                        </Card.Body>
                    </Card>
                </div>
            )}

            <div className="ms-5 mb-3 pe-5">
                <h3 className="mt-3">Bình luận</h3>
                <div className="pb-2">
                    {comments.length > 0 ? (
                        comments.map((comment) => (
                            <Card key={comment.id} className="mt-1 mb-1" >
                                <Card.Body className="pb-0" style={{paddingTop: "3px"}}>
                                    <div className="row mb-2">
                                        <div className="col-md-11 col-12 d-flex">
                                            <img
                                                src={comment.idUser.avatar}
                                                alt="avatar"
                                                className="img-fluid"
                                                style={{ width: "50px", height: "50px", objectFit: "cover", marginTop: "10px" }}
                                            />
                                            <h4 className="ms-3 mt-2">
                                                {comment.idUser.username}
                                            </h4>
                                        </div>                                        
                                        <small className="col-md-1 col-12">
                                            {formatDate(comment.ngaybinhluan)}
                                        </small>
                                    </div>
                                    <div>
                                        <p style={{marginLeft: "65px", marginTop: "10px"}}>{comment.noidung}</p>
                                    </div>
                                </Card.Body>                               
                            </Card>                           
                        ))
                    ) : (
                        <p>Chưa có bình luận nào.</p>
                    )}
                    {renderPagination()}
                </div>
            </div>            
        </Container>
    );
};

export default ActivityDetails;