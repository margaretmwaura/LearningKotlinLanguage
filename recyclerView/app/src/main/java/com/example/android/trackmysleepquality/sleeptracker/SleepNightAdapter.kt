/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.trackmysleepquality.sleeptracker

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.convertDurationToFormatted
import com.example.android.trackmysleepquality.convertNumericQualityToString
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.databinding.ListItemSleepNightBinding
import com.example.android.trackmysleepquality.generated.callback.OnClickListener

class SleepNightAdapter(val clickListener: SleepNightListener): ListAdapter<SleepNight, SleepNightAdapter.viewHolder>(DiffNightSleepCallback()) {


    override fun onBindViewHolder(holder: viewHolder, position: Int)
    {

        val item = getItem(position)
        val res = holder.itemView.context.resources

        holder.bind(getItem(position)!! ,clickListener,res )


    }

    private fun viewHolder.bind(item: SleepNight,  clickListener: SleepNightListener ,res: Resources ) {

        binding.sleep = item
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder
    {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemSleepNightBinding.inflate(layoutInflater, parent, false)
        return viewHolder(binding)
    }


//    The variables of the vieholder have been refactored inlined
    class viewHolder( val binding: ListItemSleepNightBinding) : RecyclerView.ViewHolder(binding.root)
    {

    }

    class DiffNightSleepCallback : DiffUtil.ItemCallback<SleepNight>() {
        override fun areContentsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
            return oldItem.nightId == newItem.nightId
        }

    }



}
class SleepNightListener(val clickListener: (sleepId : Long) -> Unit)
{
    fun onClick(night: SleepNight) = clickListener(night.nightId)
}