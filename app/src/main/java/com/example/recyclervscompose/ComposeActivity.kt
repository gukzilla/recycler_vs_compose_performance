package com.example.recyclervscompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AlertDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recyclervscompose.recyclerview.Entry
import com.example.recyclervscompose.repo.Repository

@OptIn(ExperimentalComposeUiApi::class)
class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data = Repository.entries

        setContent {
            MaterialTheme {
                Box(
                    modifier = Modifier.semantics {
                        // Allows to use testTag() for UiAutomator's resource-id.
                        // It can be enabled high in the compose hierarchy, 
                        // so that it's enabled for the whole subtree 
                        testTagsAsResourceId = true
                    }
                ) {
                    // Thanks to [SemanticsPropertyReceiver.testTagsAsResourceId],
                    // [Modifier.testTag]s will be propagated to resource-id
                    // and can be accessed from benchmarks.
                    Column(modifier = Modifier.fillMaxWidth()) {
                        TopAppBar(
                            title = {
                                Text(text = "ComposeList Sample")
                            }
                        )

                        LazyColumn(
                            modifier = Modifier
                                .testTag("myLazyColumn")
                        ) {
                            items(data, key = { it.contents }) { item ->
                                EntryRow(entry = item,
                                    Modifier
                                        .padding(8.dp)
                                        .clickable {
                                            AlertDialog
                                                .Builder(this@ComposeActivity)
                                                .setMessage("Item clicked")
                                                .show()
                                        })
                            }
                        }
                    }
                }
            }
        }
    }

    private fun entries(size: Int) = List(size) {
        Entry("Item $it")
    }

    companion object {
        const val EXTRA_ITEM_COUNT = "ITEM_COUNT"
    }
}

@Composable
private fun EntryRow(entry: Entry, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = entry.contents,
                modifier = Modifier
                    .padding(16.dp)
                    .wrapContentSize()
            )

            Spacer(modifier = Modifier.weight(1f))

            Checkbox(
                checked = false,
                onCheckedChange = {},
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview
@Composable
private fun EntryRowPreview() {
    val entry = Entry("Sample text")
    EntryRow(entry = entry, Modifier.padding(8.dp))
}
