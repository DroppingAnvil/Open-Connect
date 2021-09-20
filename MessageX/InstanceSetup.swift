//
//  InstanceSetup.swift
//  MessageX
//
//  Created by Dropping Anvil on 9/18/21.
//
import SwiftUI
import Foundation


struct InstanceSetup {
    
    init(_loginSuccess: Binding<Bool>, message: Binding<String>, image: Image) {
        code = []
        //TODO retreive from mem
        persistcode = [1,2,3,4,5,6]
        self.image = image
        loginSuccess = _loginSuccess
    }
    
    mutating func append(a: Int) {
        code.insert(a, at: code.count)
        //Fill bubble
        
        if code.count > 5 {
            if code == persistcode {
                //Good entry
                //message = "Unlocking"
                code = []
                image = Image("lock.open.fill")
                
            } else {
                //Bad entry
                code = []
                //message = "Incorrect PIN"
                //Clear bubbles, retry
            }
        }
    }

}
