import React, { Component } from 'react';
import { AppRegistry, Text } from 'react-native';

export default class IntentButton extends Component { 


    constructor(props) {
        super(props)
    }




    render() { 
            return (
            <Text  onPress={() => this._onItemClick()} >
                RN BUTTON  GO 2 RN  WORLD
            </Text>     
            )
    } 


    _onItemClick(item) {
        
        this.props.navigation.navigate("Home", {item: item})
       
    }

} 
