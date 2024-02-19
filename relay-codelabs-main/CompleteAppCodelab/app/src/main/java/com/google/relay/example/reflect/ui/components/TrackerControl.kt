/*
 * Copyright 2023 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.relay.example.reflect.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.google.relay.example.reflect.model.Tracker
import com.google.relay.example.reflect.model.TrackerData
import com.google.relay.example.reflect.model.TrackerType

/*
 * A component for controlling trackers.
 *
 * TrackerControl is responsible for providing interaction and state management common to all
 * tracker types, by providing a context menu with edit and delete controls.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackerControl(
    trackerData: TrackerData,
    modifier: Modifier = Modifier,
    onEditTracker: (Tracker) -> Unit = {},
    onDeleteTracker: (Tracker) -> Unit = {},
) {
    var expanded by remember { mutableStateOf(false) }
    var confirming by remember { mutableStateOf(false) }

    Box(modifier = modifier) {

        // TODO: replace with Relay tracker components
        when (trackerData.tracker.type) {
            TrackerType.BOOLEAN ->
                SwitchControl(
                    trackerData = trackerData,
                    onLongClick = { expanded = true }
                )

            TrackerType.RANGE -> {
                RangeControl(
                    trackerData = trackerData,
                    onLongClick = { expanded = true }
                )
            }

            TrackerType.COUNT ->
                ValueControl(
                    trackerData = trackerData,
                    onLongClick = { expanded = true },
                )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            DropdownMenuItem(
                text = { Text("Edit") },
                onClick = { onEditTracker(trackerData.tracker) },
                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
            )
            DropdownMenuItem(
                text = { Text("Delete") },
                onClick = { confirming = true },
                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
            )
        }
    }

    if (confirming) {
        AlertDialog(
            onDismissRequest = { confirming = false },
            title = {
                Text(text = "Remove tracker?")
            },
            text = {
                Text("All historical data will also be removed.")
            },
            confirmButton = {
                Button(
                    onClick = {
                        confirming = false
                        onDeleteTracker(trackerData.tracker)
                    }
                ) {
                    Text("Remove")
                }
            },
            dismissButton = {
                Button(
                    onClick = { confirming = false }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}