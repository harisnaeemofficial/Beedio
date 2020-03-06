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

package marabillas.loremar.beedio.download.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import dagger.android.support.DaggerFragment
import marabillas.loremar.beedio.download.R
import marabillas.loremar.beedio.download.adapters.CompletedAdapter
import marabillas.loremar.beedio.download.viewmodels.CompletedVM
import javax.inject.Inject

class CompletedFragment @Inject constructor() : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var completedAdapter: CompletedAdapter

    private lateinit var completedVM: CompletedVM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return context?.run {
            RecyclerView(this).apply {
                adapter = completedAdapter
                layoutManager = LinearLayoutManager(context)
                layoutParams = ConstraintLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                (itemAnimator as SimpleItemAnimator?)?.supportsChangeAnimations = false

                val divider = object : DividerItemDecoration(context, VERTICAL) {}
                ResourcesCompat.getDrawable(resources, R.drawable.completed_divider, null)
                        ?.let { divider.setDrawable(it) }
                addItemDecoration(divider)
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            completedVM = ViewModelProvider(it::getViewModelStore, viewModelFactory).get(CompletedVM::class.java)
        }
    }

    override fun onStart() {
        super.onStart()
        completedVM.observeItemDetailsFetched(this, Observer {
            completedAdapter.addDetails(it)
        })
        completedVM.loadList { completedAdapter.loadData(it) }
    }
}