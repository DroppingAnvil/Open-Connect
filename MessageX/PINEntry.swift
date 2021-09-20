//
//  PINEntry.swift
//  MessageX
//
//  Created by Dropping Anvil on 9/20/21.
//

import SwiftUI

struct PINEntry: View {
    static var code: Array<Int> = []
    static var persistcode: Array<Int> = [1,2,3,4,5,6]
    @State static var message: String = "MessageX"
    @State static var image: Image = Image("lock")
    @Binding var loginSuccess: Bool
    
    let data = (1...100).map { "Item \($0)" }
    var body: some View {
        ZStack {
            Color.red.ignoresSafeArea()
            LazyHGrid(rows: [GridItem(.flexible()), GridItem(.flexible())], spacing: 20) {
                
                Button(action: {append(a: 1)}) {
                    Image("NumericButton1")
                }
                Button(action: {append(a: 2)}) {
                    Image("NumericButton2")
                }
                Button(action: {append(a: 3)}) {
                    Image("NumericButton3")
                }
                Button(action: {append(a: 4)}) {
                    Image("NumericButton4")
                }
                Button(action: {append(a: 5)}) {
                    Image("NumericButton5")
                }
                Button(action: {append(a: 6)}) {
                    Image("NumericButton6")
                }
                Button(action: {append(a: 7)}) {
                    Image("NumericButton7")
                }
                Button(action: {append(a: 8)}) {
                    Image("NumericButton8")
                }
                Button(action: {append(a: 9)}) {
                    Image("NumericButton9")
                }
                Button(action: {append(a: 0)}) {
                    Image("NumericButton0")
                }
            }
        .frame(maxHeight: 150)
        .padding(.top, 500.0)
    }
        }
    func append(a: Int) {
        PINEntry.code.insert(a, at: PINEntry.code.count)
        //Fill bubble
        
        if PINEntry.code.count > 5 {
            if  (
                PINEntry.code[0] == PINEntry.persistcode[0] &&
                PINEntry.code[1] == PINEntry.persistcode[1] &&
                PINEntry.code[2] == PINEntry.persistcode[2] &&
                PINEntry.code[3] == PINEntry.persistcode[3] &&
                PINEntry.code[4] == PINEntry.persistcode[4] &&
                PINEntry.code[5] == PINEntry.persistcode[5]
            ) {
                //Good entry
                loginSuccess = true
                print("Login Success")
                PINEntry.code = []
                PINEntry.image = Image("lock.open")
                
            } else {
                //Bad entry
                PINEntry.code = []
                print("Login Fail")
                //message = "Incorrect PIN"
                //Clear bubbles, retry
            }
        }
        print(PINEntry.code)
    }
    func getPersistCode() {
        //TODO
    }
    }

struct PINEntry_Previews {
}
