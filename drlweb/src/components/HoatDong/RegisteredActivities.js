import { useEffect, useState } from "react";
import { Spinner, Button, Container, Card, Alert, Form } from "react-bootstrap";
import { useNavigate, useParams } from "react-router-dom";
import { authApi, endpoints } from "../../configs/APIs";

const RegisteredActivities = () => {
    const [activities, setActivities] = useState(null);
    const nav = useNavigate();
    const { userId } = useParams();

    const [showUploadForm, setShowUploadForm] = useState(false);
    const [idHoatDongValue, setIdHoatDongValue] = useState(null);
    const [showSuccessMessage, setShowSuccessMessage] = useState(false);

    const loadActivities = async () => {
        try {
            const api = authApi(); 
            let res = await api.get(endpoints['registered-activities']);
            setActivities(res.data);
        } catch (ex) {
            console.error(ex);
        }
    }

    useEffect(() => {
        loadActivities();
    })

    const handleBaoThieuClick  = (idHoatDong) => () => {
        setShowUploadForm(true); // Khi click vào Báo thiếu, hiển thị form upload
        setIdHoatDongValue(idHoatDong);
    }

    const closeUploadForm = () => {
        setShowUploadForm(false);
    } 

    const handleUploadSubmit = async (event) => {
        event.preventDefault();
        const formData = new FormData();
        formData.append('idHoatDong', idHoatDongValue); // Thay 'idHoatDongValue' bằng giá trị thực tế
        formData.append('file', event.target.file.files[0]); // Lấy file từ input file

        try {
            await authApi().post('/api/baothieu/', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            });
            setShowSuccessMessage(true);
            setShowUploadForm(false);
        } catch (error) {
            console.error('Upload error:', error);
        }
    }

    useEffect(() => {
        loadActivities();
    }, [userId])


    return (
        <div className="feed-container mt-4 mb-4">
            {activities === null ? (
                <Spinner animation="grow" variant="primary" />
            ) : (
                <Container>
                    <h1 className="text-center mb-3">Hoạt Động Đã Đăng Ký</h1>
                    {activities.map((a, index) => (
                        <Card key={a.id} className={`mb-3 ${index % 2 === 0 ? 'bg-light' : 'bg-white'}`}>
                            <Card.Body>
                                <Card.Title>{a.tenhoatdong}</Card.Title>
                                <Card.Text>
                                    <strong>Tiêu chí:</strong> {a.tieuchi}<br />
                                    <strong>Điểm:</strong> {a.diem}<br />
                                    <strong>Địa điểm:</strong> {a.diadiem}<br />
                                </Card.Text>
                                <Button variant="info" size="md" style={{ marginRight: '10px'}} onClick={() => nav(`/activities/${a.id}`)}>Xem chi tiết</Button>
                                <Button variant="warning" size="md" onClick={handleBaoThieuClick(a.id)}>Báo thiếu</Button>
                                {showUploadForm && idHoatDongValue === a.id && (
                                    <Form onSubmit={handleUploadSubmit} className="mt-2">
                                        <Form.Group controlId="file">
                                            <Form.Label><strong>Chọn tệp minh chứng:</strong></Form.Label>
                                            <Form.Control type="file" name="file" />
                                        </Form.Group>
                                        <Button variant="primary" type="submit" className="mt-3">Gửi báo thiếu</Button>
                                        <Button onClick={closeUploadForm} className="mt-3 ms-2 text-center btn btn-danger ">Đóng</Button>
                                    </Form>
                                )}

                                {showSuccessMessage && idHoatDongValue === a.id && (
                                    <Alert variant="success" className="mt-3">
                                        Báo thiếu đã được gửi thành công!
                                    </Alert>
                                    
                                )}
                            </Card.Body>
                        </Card>
                    ))}
                </Container>
            )}
        </div>
    );
}

export default RegisteredActivities;
