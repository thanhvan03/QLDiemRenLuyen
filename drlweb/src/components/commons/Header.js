import { useContext, useEffect, useState } from "react";
import { Container, Nav, Navbar, NavDropdown } from "react-bootstrap";
import { Link } from "react-router-dom";
import { MyDispatchContext, MyUserContext } from "../../configs/Context";
import APIs, { endpoints } from "../../configs/APIs";

const Header = () => {
    const user = useContext(MyUserContext);
    const dispatch = useContext(MyDispatchContext);

    const [hocky, setHocKy] = useState(null);

    const loadHocKy = async () => {
        try {
            let res = await APIs.get(endpoints['hockys']);
            setHocKy(res.data);
        } catch (ex) {
            console.error(ex);
        }
    }

    useEffect(() => {
        loadHocKy();
    }, [])  

    return (
        <Navbar expand="lg" className="navbar" style={{ backgroundColor: "#0a58ca" }}>
            <Container>
                <Navbar.Brand style={{ color: "#FFFFFF" }}>Forging points</Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="me-auto">
                        <Link to="/" className="nav-link" style={{ color: "#FFFFFF" }}>
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" style={{marginTop: "-5", marginRight: "5px"}} viewBox="0 0 16 16">
                            <path d="M8.707 1.5a1 1 0 0 0-1.414 0L.646 8.146a.5.5 0 0 0 .708.708L2 8.207V13.5A1.5 1.5 0 0 0 3.5 15h9a1.5 1.5 0 0 0 1.5-1.5V8.207l.646.647a.5.5 0 0 0 .708-.708L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293zM13 7.207V13.5a.5.5 0 0 1-.5.5h-9a.5.5 0 0 1-.5-.5V7.207l5-5z"/>
                        </svg>
                        Trang chủ</Link>

                        <Nav.Link as={Link} to="/registered-activities" style={{ color: "#FFFFFF" }}>Hoạt động đã đăng ký</Nav.Link>
                        <Nav.Link as={Link} to="/participated-activities" style={{ color: "#FFFFFF" }}>Hoạt động đã tham gia</Nav.Link>
                        
                        {hocky !== null && (
                            <NavDropdown title={<span style={{ color: "#FFFFFF" }}>Bảng điểm thành tích</span>} id="basic-nav-dropdown">
                                {hocky.map((h) => {
                                    let url = `/diem-ren-luyen/${h.id}`;
                                    return <Link to={url} className="nav-link" key={h.id}>{h.hocky} - {h.namhoc}</Link>;
                                })}
                            </NavDropdown>
                        )}

                        {user !== null ? (
                            <>
                                <Link to="/userinfo" className="nav-link" style={{ color: "#FFFFFF" }}>
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" style={{marginTop: "-5", marginRight: "5px"}} viewBox="0 0 16 16">
                                    <path d="M15 14s1 0 1-1-1-4-5-4-5 3-5 4 1 1 1 1zm-7.978-1L7 12.996c.001-.264.167-1.03.76-1.72C8.312 10.629 9.282 10 11 10c1.717 0 2.687.63 3.24 1.276.593.69.758 1.457.76 1.72l-.008.002-.014.002zM11 7a2 2 0 1 0 0-4 2 2 0 0 0 0 4m3-2a3 3 0 1 1-6 0 3 3 0 0 1 6 0M6.936 9.28a6 6 0 0 0-1.23-.247A7 7 0 0 0 5 9c-4 0-5 3-5 4q0 1 1 1h4.216A2.24 2.24 0 0 1 5 13c0-1.01.377-2.042 1.09-2.904.243-.294.526-.569.846-.816M4.92 10A5.5 5.5 0 0 0 4 13H1c0-.26.164-1.03.76-1.724.545-.636 1.492-1.256 3.16-1.275ZM1.5 5.5a3 3 0 1 1 6 0 3 3 0 0 1-6 0m3-2a2 2 0 1 0 0 4 2 2 0 0 0 0-4"/>
                                </svg>
                                Thông Tin</Link>
                                <Link to="/chat" className="nav-link" style={{ color: "#FFFFFF" }}>
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" style={{marginTop: "-5", marginRight: "5px"}} viewBox="0 0 16 16">
                                    <path d="M2.678 11.894a1 1 0 0 1 .287.801 11 11 0 0 1-.398 2c1.395-.323 2.247-.697 2.634-.893a1 1 0 0 1 .71-.074A8 8 0 0 0 8 14c3.996 0 7-2.807 7-6s-3.004-6-7-6-7 2.808-7 6c0 1.468.617 2.83 1.678 3.894m-.493 3.905a22 22 0 0 1-.713.129c-.2.032-.352-.176-.273-.362a10 10 0 0 0 .244-.637l.003-.01c.248-.72.45-1.548.524-2.319C.743 11.37 0 9.76 0 8c0-3.866 3.582-7 8-7s8 3.134 8 7-3.582 7-8 7a9 9 0 0 1-2.347-.306c-.52.263-1.639.742-3.468 1.105"/>
                                </svg>
                                Chat</Link>
                                <Link to="/" className="nav-link" style={{ color: "#ffff00" }}>Xin chào {user.username}</Link>
                                <Link to="/" onClick={() => dispatch({ type: "logout" })} className="nav-link" style={{ color: "#FFA500" }}>Đăng xuất</Link>
                            </>
                        ) : (
                            <>
                                <Link to="/login" className="nav-link" style={{ color: "#ffff00" }}>Đăng nhập</Link>
                                <Link to="/register" className="nav-link" style={{ color: "#FFA500" }}>Đăng ký</Link>
                            </>
                        )}
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
}

export default Header;
