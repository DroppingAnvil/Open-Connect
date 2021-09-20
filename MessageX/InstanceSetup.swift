//
//  InstanceSetup.swift
//  MessageX
//
//  Created by Dropping Anvil on 9/18/21.
//
import SwiftUI
import Foundation


class InstanceSetup {
    var code: Array<Int>
    var persistcode: Array<Int>
    var lockScreen: ContentView
    
    init(lockView: ContentView) {
        code = []
        //TODO retreive from mem
        persistcode = [1,2,3,4,5,6]
        lockScreen = lockView
    }
    
    func append(a: Int) {
        code.insert(a, at: code.count)
        //Fill bubble
        
        if code.count > 4 {
            if code == persistcode {
                //Good entry
            } else {
                //Bad entry
                code = []
                //Clear bubbles, retry
            }
        }
    }

}
