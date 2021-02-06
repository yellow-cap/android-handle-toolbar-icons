package com.sandbox.handletoolbaricons

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView

class MainActivity : AppCompatActivity() {
  private lateinit var fragmentContainerView: FragmentContainerView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    supportFragmentManager.beginTransaction()
      .add(R.id.fragment_container_view, Fragment1(), FragmentTag.Fragment1.tag)
      .commit()
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu, menu)

    return super.onCreateOptionsMenu(menu)
  }

  override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
    supportFragmentManager.fragments.forEach {
      when {
        it.tag == FragmentTag.Fragment1.tag && it.isVisible -> {
          menu?.findItem(R.id.action_fragment1)?.isVisible = true
          menu?.findItem(R.id.action_fragment2)?.isVisible = true
        }
        it.tag == FragmentTag.Fragment2.tag && it.isVisible -> menu?.findItem(R.id.action_fragment2)?.isVisible = true
        it.tag == FragmentTag.Fragment3.tag && it.isVisible -> menu?.findItem(R.id.action_fragment3)?.isVisible = true
      }
    }

    return super.onPrepareOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
      R.id.action_fragment1 -> {
        supportFragmentManager.beginTransaction().apply {
          replace(R.id.fragment_container_view, Fragment2(), FragmentTag.Fragment2.tag)
          commit()
        }

        true
      }

      R.id.action_fragment2 -> {
        supportFragmentManager.beginTransaction().apply {
          replace(R.id.fragment_container_view, Fragment3(), FragmentTag.Fragment3.tag)
          commit()
        }

        true
      }

      R.id.action_fragment3 -> {
        supportFragmentManager.beginTransaction().apply {
          replace(R.id.fragment_container_view, Fragment1(), FragmentTag.Fragment1.tag)
          commit()
        }

        true
      }

      else -> {
        super.onOptionsItemSelected(item)
      }
    }

  private fun initViews() {
    fragmentContainerView = findViewById(R.id.fragment_container_view)
  }
}