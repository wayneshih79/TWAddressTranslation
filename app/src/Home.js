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
                            <Row className='mt-2'>
                            <Col><Label for="address">中文地址</Label></Col>
                            </Row>
                            <Row className='mt-2'>
                            <Col xs="4">
                            <Input type="address" name="address" id="address" placeholder="請輸入地址" onChange={this.handleAddressInputChange} />
                            </Col>
                            </Row>
                            <Row className='mt-2'>
                            <Col xs={{ size: '4', offset: 4 }}>
                            <Button onClick={this.clickHandler.bind(this)}>翻譯</Button>
                            </Col>
                            </Row>
                            <Row className='mt-2'>
                            <Label for="addressResult">英文地址</Label>
                            </Row>
                            <Row className='mt-2'>
                            <Col xs="6">
                            <Input type="addressResult" name="addressResult" id="addressResult" value={this.state.translateAddress}/>
                            </Col>
                            </Row>
                </Container>
            </div>
        );
    }
}

export default Home;