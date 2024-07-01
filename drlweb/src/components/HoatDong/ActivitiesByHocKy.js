// import { useEffect, useState } from "react";
// import { Spinner, Button, Container, Card } from "react-bootstrap";
// import { useNavigate, useParams, useSearchParams } from "react-router-dom";
// import APIs, { endpoints } from "../../configs/APIs";

// const ActivitiesByHocKy = () => {
//     const [activities, setActivities] = useState(null);
//     const nav = useNavigate();
//     const [loading, setLoading] = useState(false);
//     const { hocKyId } = useParams();
//     const [q, ] = useSearchParams();

//     const loadActivities = async () => {
//         setLoading(true);
//         try {
//             let url = endpoints['activities-hocky'];

//             let hocKyId = q.get('hocKyId');
//             if (hocKyId) {
//                 url = `${url}&hocKyId=${hocKyId}`;
//             } 

//             let res = await APIs.get(url);
//             setActivities(res.data);
//         } catch (ex) {
//             console.error(ex);
//         } finally {
//             setLoading(false);
//         }
//     }

//     useEffect(() => {
//         loadActivities();
//     }, [q])

//     return (
//         <div className="feed-container">
//             {activities === null ? (
//                 <Spinner animation="grow" variant="primary" />
//             ) : (
//                 <Container>
//                     {activities.map((a, index) => (
//                         <Card key={a.id} className={`mb-3 ${index % 2 === 0 ? 'bg-light' : 'bg-white'}`}>
//                             <Card.Body>
//                                 <Card.Title>{a.tenhoatdong}</Card.Title>
//                                 <Card.Text>
//                                     <strong>Tiêu chí:</strong> {a.tieuchi}<br />
//                                     <strong>Điểm:</strong> {a.diem}<br />
//                                     <strong>Địa điểm:</strong> {a.diadiem}<br />
//                                 </Card.Text>
//                                 <Button variant="info" size="md" style={{ marginRight: '10px'}} onClick={() => nav(`/activities/${a.id}`)}>Xem chi tiết</Button>
//                                 <Button variant="success" size="md">Đăng ký</Button>
//                             </Card.Body>
//                         </Card>
//                     ))}
//                 </Container>
//             )}
//         </div>
//     );
// }

// export default ActivitiesByHocKy;
