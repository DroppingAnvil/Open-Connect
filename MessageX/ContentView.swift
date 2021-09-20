//
//  ContentView.swift
//  MessageX
//
//  Created by Dropping Anvil on 9/18/21.
//

import SwiftUI

struct ContentView: View {
    var iSetup: InstanceSetup
    var body: some View {
        ZStack {
            Color.black.ignoresSafeArea()
        }
        Text("MessageX")
            .font(.largeTitle)
            .fontWeight(.ultraLight)
            .foregroundColor(Color.white)
            .multilineTextAlignment(.center)
            .padding(.horizontal, 50.0)
        Button(role: <#T##ButtonRole?#>) {
            InstanceSetup.append(1)
        } label: {
            <#code#>
        }

    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
            .preferredColorScheme(.dark)
    }
}
