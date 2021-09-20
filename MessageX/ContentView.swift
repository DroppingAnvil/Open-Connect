//
//  ContentView.swift
//  MessageX
//
//  Created by Dropping Anvil on 9/18/21.
//

import SwiftUI

struct ContentView: View {
    @ObservedObject var iSetup: InstanceSetup
    init() {
        iSetup = InstanceSetup.init()
    }
    let data = (1...100).map { "Item \($0)" }
    var body: some View {
        ZStack {
            Color.black.ignoresSafeArea()
            LazyHGrid(rows: [GridItem(.flexible()), GridItem(.flexible())], spacing: 20) {
                
                Button(action: {iSetup.append(a: 1)}) {
                    Image("NumericButton1")
                }
                Button(action: {iSetup.append(a: 2)}) {
                    Image("NumericButton2")
                }
                Button(action: {iSetup.append(a: 3)}) {
                    Image("NumericButton3")
                }
                Button(action: {iSetup.append(a: 4)}) {
                    Image("NumericButton4")
                }
                Button(action: {iSetup.append(a: 5)}) {
                    Image("NumericButton5")
                }
                Button(action: {iSetup.append(a: 6)}) {
                    Image("NumericButton6")
                }
                Button(action: {iSetup.append(a: 7)}) {
                    Image("NumericButton7")
                }
                Button(action: {iSetup.append(a: 8)}) {
                    Image("NumericButton8")
                }
                Button(action: {iSetup.append(a: 9)}) {
                    Image("NumericButton9")
                }
                Button(action: {iSetup.append(a: 0)}) {
                    Image("NumericButton0")
                }
            }
        .frame(maxHeight: 150)
        .padding(.top, 500.0)
    }
        }
    }

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
            //.preferredColorScheme(.dark)
    }
}
