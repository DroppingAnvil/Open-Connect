//
//  InstanceSetup.swift
//  MessageX
//
//  Created by Dropping Anvil on 9/18/21.
//
import SwiftUI
import Foundation


class InstanceSetup: ObservableObject {
    var code: Array<Int>
    var persistcode: Array<Int>
    @Published var message: String
    
    init() {
        code = []
        //TODO retreive from mem
        persistcode = [1,2,3,4,5,6]
        message = "MessageX"
    }
    
    func append(a: Int) {
        code.insert(a, at: code.count)
        //Fill bubble
        
        if code.count > 5 {
            if code == persistcode {
                //Good entry
                message = "Unlocking"
            } else {
                //Bad entry
                code = []
                message = "Incorrect PIN"
                //Clear bubbles, retry
            }
        }
    }

}
