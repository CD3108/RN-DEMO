import React, {Component} from "react"

import { 
    ToastAndroid,   
    StyleSheet,
    Text,
    View,
    Image,
    DeviceEventEmitter,
} from "react-native"
import {NativeModules} from 'react-native';
/**
 * 主页
 */
export default class Profile extends Component {
    static navigationOptions = {
        headerTitle: '首页',//对页面的配置
        tabBarLabel: '首页',
        tabBarIcon:<View style={{height:20,width:20,backgroundColor:'red'}}/>
    };

    state = {
        /*初始化msg*/
        msg: '暂时无信息111',
    }

    handleAndroidMessage(androidMeg) {
        ToastAndroid.show(androidMeg, ToastAndroid.SHORT, ToastAndroid.CENTER);
        this.setState({msg: androidMeg});
    }

    componentWillMount() {
        DeviceEventEmitter.addListener('AndroidToRNMessage', this.handleAndroidMessage.bind(this));
    }

    componentWillUnmount() {
        DeviceEventEmitter.removeAllListeners();
    }



    render() {
        let pic = {
        uri:'http://p2.so.qhmsg.com/bdr/_240_/t01870507c38a144b17.jpg'
        };
        return (
            <View>
                <Text  onPress={() => this._onItemClick()} style={styles.welcome}>
                    这是一个常量:{NativeModules.ExampleInterface.AA}
                </Text>

                <Text style={styles.welcome}>
                    {this.state.msg}
                </Text>
                
                
              
            <Image source={pic} style={{width: 193,height: 110}}/>

            <Image source={pic} style={{width: 193,height: 110}}/>

           
                
            </View>
        )
    }


    _onItemClick() {
        //NativeModules.ExampleInterface.handleMessage("I press handleMessage.");
        
        //NativeModules.ExampleInterface.FUCK_U("I press FUCK_U");
        //NativeModules.ExampleInterface.handleCallback('这是来自 JS 的msg', (msg) => {
            //console.log(msg);
            //alert(msg);
            //ToastAndroid.show(msg, ToastAndroid.SHORT, ToastAndroid.CENTER);
       // });

       //<Image style = {{width: 300, height: 200}}
               // source = {require('./res/fuck.png')}
               // resizeMode = {'contain'}
           // />

       NativeModules.ExampleInterface.Go2Contacts("I press Go2Contacts");
    }
}

const styles = StyleSheet.create({
   
    welcome: {
        fontSize: 20,
        textAlign: 'center',
        margin: 10,
    }
});
