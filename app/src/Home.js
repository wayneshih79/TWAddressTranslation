import React, { Component } from 'react';
import './App.css';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';
import { Button, Form, Label, Input, Container, Row, Col } from 'reactstrap';

class Home extends Component {

    constructor(props) {
            super(props);
            this.state = {translateAddress: '', originalAddress: ''};
     }

     handleAddressInputChange = (event) => {
        event.preventDefault()
        this.setState({originalAddress: event.target.value})
     }

    clickHandler() {
         const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ address: this.state.originalAddress })
        };
        fetch('api/translate/address', requestOptions)
                    .then(response => response.json())
        .then(data => this.setState({translateAddress: data.address}));
    }
    render() {
        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <Button color="link"><Link to="/groups">Manage JUG Tour</Link></Button>
                            <Row>
                            <Label for="address">地址</Label>
                            </Row>
                            <Row>
                            <Col xs="10">
                            <Input type="address" name="address" id="address" placeholder="請輸入地址" onChange={this.handleAddressInputChange} />
                            </Col>
                            </Row>
                            <Button onClick={this.clickHandler.bind(this)}>Submit</Button>
                            <Row>
                            <Label for="addressResult">英文地址</Label>
                            </Row>
                            <Row>
                            <Input type="addressResult" name="addressResult" id="addressResult" value={this.state.translateAddress}/>
                            </Row>
                </Container>
            </div>
        );
    }
}

export default Home;