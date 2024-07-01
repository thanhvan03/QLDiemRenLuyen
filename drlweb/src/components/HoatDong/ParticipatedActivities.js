import { useEffect, useState } from "react";
import { Spinner, Button, Container, Card } from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";
import { authApi, endpoints } from "../../configs/APIs";

const ParticipatedActivities = () => {
    const [activities, setActivities] = useState(null);
    const nav = useNavigate();

    const loadActivities = async () => {
        try {
            const api = authApi(); 
            let res = await api.get(endpoints["participated-activities"]);
            setActivities(res.data);
        } catch (ex) {
            console.error(ex);
        }
    }

    useEffect(() => {
        loadActivities();
    })

    const formatDate = (timestamp) => {
        const date = new Date(timestamp);
        const day = date.getDate().toString().padStart(2, '0');
        const month = (date.getMonth() + 1).toString().padStart(2, '0');
        const year = date.getFullYear();
        return `${day}/${month}/${year}`;
    }

    return (
        <div className="feed-container">
            {activities === null ? (
                <Spinner animation="grow" variant="primary" />
            ) : (
                <Container>
                    <h1 className="text-center mt-3 mb-3">Hoạt Động Đã Tham Gia</h1>
                    {activities.map((activity, index) => (
                        <Card key={activity.id} className={`mb-3 mt-2 ${index % 2 === 0 ? 'bg-light' : 'bg-white'}`}  
                        style={{ width: '85%', margin: 'auto' }}>
                        <div className="row" > 
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
                                    </Card.Text>
                                    <Button variant="info" size="md" style={{ marginRight: '10px' }} onClick={() => nav(`/activities/${activity.id}`)}>Xem chi tiết</Button>
                                </Card.Body>                                           
                            </div> 
                            <div className="col-md-2 col-12 ps-5 pt-2">
                                <small> {formatDate(activity.ngaybatdau)} </small>
                            </div>    
                            </div>                                    
                        </Card> 
                    ))}
                </Container>
            )}
        </div>
    );
}

export default ParticipatedActivities;
