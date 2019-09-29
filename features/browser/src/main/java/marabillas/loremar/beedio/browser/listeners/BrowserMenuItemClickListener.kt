/*
 * Beedio is an Android app for downloading videos
 * Copyright (C) 2019 Loremar Marabillas
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package marabillas.loremar.beedio.browser.listeners

import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import marabillas.loremar.beedio.browser.R
import marabillas.loremar.beedio.browser.uicontrollers.BrowserSearchWidgetControllerInterface
import marabillas.loremar.beedio.browser.uicontrollers.WebPageNavigatorInterface
import marabillas.loremar.beedio.browser.uicontrollers.WebViewSwitcherInterface
import javax.inject.Inject

class BrowserMenuItemClickListener @Inject constructor() : Toolbar.OnMenuItemClickListener {
    var webPageNavigator: WebPageNavigatorInterface? = null
    var searchWidgetController: BrowserSearchWidgetControllerInterface? = null
    var webViewSwitcher: WebViewSwitcherInterface? = null

    override fun onMenuItemClick(item: MenuItem?): Boolean {

        when (item?.itemId) {
            R.id.browser_menu_back -> webPageNavigator?.goBack()
            R.id.browser_menu_forward -> webPageNavigator?.goForward()
            R.id.browser_menu_reload -> webPageNavigator?.reloadPage()
            R.id.browser_menu_add_window -> searchWidgetController?.showSearchWidget()
            R.id.browser_menu_close_window -> webViewSwitcher?.closeWebView()
        }

        return true
    }
}