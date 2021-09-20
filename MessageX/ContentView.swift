//
//  ContentView.swift
//  MessageX
//
//  Created by Dropping Anvil on 9/18/21.
//

import SwiftUI

struct ContentView: View {
    @State var loginSuccess: Bool = false

    var body: some View {
        
        if (loginSuccess) {
                Home()
            } else {
                PINEntry(loginSuccess: $loginSuccess)
            }
    }

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
            //.preferredColorScheme(.dark)
    }
}
}
