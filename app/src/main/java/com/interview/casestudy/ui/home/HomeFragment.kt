package com.interview.casestudy.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.interview.casestudy.R
import com.interview.casestudy.databinding.FragmentHomeBinding
import com.interview.casestudy.model.HorizontalList
import com.interview.casestudy.model.Meditation
import com.interview.casestudy.model.Story
import com.interview.casestudy.ui.MainViewModel
import com.interview.casestudy.util.eventObserve
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()

    private val homeViewModel: HomeViewModel by viewModels()

    private lateinit var binding: FragmentHomeBinding

    var concatAdapter: ConcatAdapter = ConcatAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvHome.adapter = concatAdapter

        eventObserve(homeViewModel.blobLiveData) {
            meditationTitle()
            meditationList(it.meditations.orEmpty().toMutableList())
            if (it.isBannerEnabled == true) {
                bannerItem()
            }
            storiesTitle()
            storiesList(it.stories.orEmpty().toMutableList())
        }

        homeViewModel.progressLiveData.observe(viewLifecycleOwner, {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        })

        homeViewModel.showErrorLiveData.observe(viewLifecycleOwner, {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(getString(R.string.title_error))
                .setMessage(it)
                .show()
        })
    }

    private fun meditationTitle() {
        concatAdapter.addAdapter(SingleRecyclerAdapter<Unit, String>(R.layout.item_title).apply {
            setData(getString(R.string.title_meditations))
        })
    }

    private fun meditationList(meditationList: MutableList<Meditation>) {
        concatAdapter.addAdapter(SingleRecyclerAdapter<Unit, HorizontalList>(R.layout.item_horizontal_list).apply {
            setData(
                HorizontalList(
                    SingleRecyclerAdapter<Unit, Meditation>(R.layout.item_meditation).apply {
                        setData(meditationList)
                        itemClickListener {
                            mainViewModel.getDetail(it.mapToDetailItem())
                            findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
                        }
                    },
                    itemDecoration = SpaceItemDecoration(
                        resources.getDimensionPixelSize(R.dimen.divider_margin),
                        false
                    )
                ),
            )
        })
    }

    private fun bannerItem() {
        concatAdapter.addAdapter(SingleRecyclerAdapter<Unit, String>(R.layout.item_banner).apply {
            setData(getString(R.string.text_banner, homeViewModel.getUsername()))
        })
    }

    private fun storiesTitle() {
        concatAdapter.addAdapter(SingleRecyclerAdapter<Unit, String>(R.layout.item_title).apply {
            setData(getString(R.string.title_stories))
        })
    }

    private fun storiesList(storyList: MutableList<Story>) {
        concatAdapter.addAdapter(SingleRecyclerAdapter<Unit, HorizontalList>(R.layout.item_grid_list).apply {
            setData(
                HorizontalList(
                    SingleRecyclerAdapter<Unit, Story>(R.layout.item_story).apply {
                        setData(storyList)
                        itemClickListener {
                            mainViewModel.getDetail(it.mapToDetailItem())
                            findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
                        }
                    },
                    itemDecoration = SpaceItemDecoration(
                        resources.getDimensionPixelSize(R.dimen.divider_margin),
                        false
                    )
                )
            )
        })
    }

}