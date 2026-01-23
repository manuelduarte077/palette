package dev.donmanuel.app.palette

import platform.UIKit.UIPasteboard

actual fun copyToClipboard(text: String) {

    UIPasteboard.generalPasteboard.string = text
}