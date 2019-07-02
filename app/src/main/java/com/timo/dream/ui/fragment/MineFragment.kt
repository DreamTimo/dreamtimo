package com.timo.dream.ui.fragment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.text.Html
import android.view.View
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.previewlibrary.GPreviewBuilder
import com.previewlibrary.view.BasePhotoFragment
import com.timo.base.BaseTools
import com.timo.base.base.base_fragment.BaseFragment
import com.timo.dream.R
import com.timo.dream.bean.ImagePreviewBean
import com.timo.dream.ui.activity.projectweb.ProjectWebActivity
import kotlinx.android.synthetic.main.fragment_mine.*
import me.kareluo.imaging.IMGEditActivity
import java.io.File
import java.util.*

/**
 * Created by lykj on 2017/9/12.
 */

class MineFragment : BaseFragment(), View.OnClickListener {
    private var imageData: ArrayList<ImagePreviewBean>? = null
    private var httpData: ArrayList<ImagePreviewBean>? = null
    private var projectData: ArrayList<ImagePreviewBean>? = null
    private var recyclerData: ArrayList<ImagePreviewBean>? = null

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.tv_mayun_project -> {
                startActivityNoFinish(ProjectWebActivity::class.java, "码云项目")
            }
            R.id.tv_project -> {
                startActivityNoFinish(ProjectWebActivity::class.java)
            }
            R.id.tv_old_work -> {
                if (imageData == null) {
                    imageData = ArrayList<ImagePreviewBean>()
                    imageData!!.add(ImagePreviewBean("12"))
                    imageData!!.add(ImagePreviewBean("13"))
                    imageData!!.add(ImagePreviewBean("11"))
                    imageData!!.add(ImagePreviewBean("9"))
                    imageData!!.add(ImagePreviewBean("7"))
                    imageData!!.add(ImagePreviewBean("5"))
                    imageData!!.add(ImagePreviewBean("4"))
                    imageData!!.add(ImagePreviewBean("3"))
                    imageData!!.add(ImagePreviewBean("10"))
                    imageData!!.add(ImagePreviewBean("16"))
                    imageData!!.add(ImagePreviewBean("17"))
                    imageData!!.add(ImagePreviewBean("18"))
                    imageData!!.add(ImagePreviewBean("6"))
                    imageData!!.add(ImagePreviewBean("8"))
                    imageData!!.add(ImagePreviewBean("14"))
                    imageData!!.add(ImagePreviewBean("15"))
                    imageData!!.add(ImagePreviewBean("2"))
                    imageData!!.add(ImagePreviewBean("1"))

                }
                GPreviewBuilder.from(this)
                        .setData(imageData!!)
                        .setUserFragment(BasePhotoFragment::class.java)
                        .setCurrentIndex(0)
                        .setSingleFling(true)
                        .setType(GPreviewBuilder.IndicatorType.Number)
                        .start()
            }
            R.id.tv_project_description -> {
                if (projectData == null) {
                    projectData = ArrayList<ImagePreviewBean>()
                    projectData!!.add(ImagePreviewBean("24"))
                    projectData!!.add(ImagePreviewBean("25"))
                    projectData!!.add(ImagePreviewBean("26"))
                    projectData!!.add(ImagePreviewBean("27"))
                    projectData!!.add(ImagePreviewBean("28"))
                    projectData!!.add(ImagePreviewBean("29"))
                    projectData!!.add(ImagePreviewBean("30"))
                }
                GPreviewBuilder.from(this)
                        .setData(projectData!!)
                        .setUserFragment(BasePhotoFragment::class.java)
                        .setCurrentIndex(0)
                        .setSingleFling(true)
                        .setType(GPreviewBuilder.IndicatorType.Number)
                        .start()
            }
            R.id.tv_recyclerview -> {
                if (recyclerData == null) {
                    recyclerData = ArrayList<ImagePreviewBean>()
                    recyclerData!!.add(ImagePreviewBean("31"))
                    recyclerData!!.add(ImagePreviewBean("32"))
                    recyclerData!!.add(ImagePreviewBean("33"))
                    recyclerData!!.add(ImagePreviewBean("34"))
                }
                GPreviewBuilder.from(this)
                        .setData(recyclerData!!)
                        .setUserFragment(BasePhotoFragment::class.java)
                        .setCurrentIndex(0)
                        .setSingleFling(true)
                        .setType(GPreviewBuilder.IndicatorType.Number)
                        .start()
            }
            R.id.tv_choice_image -> {
                PictureSelector.create(this)
                        .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                        .maxSelectNum(1)// 最大图片选择数量 int
                        .minSelectNum(1)// 最小选择数量 int
                        .imageSpanCount(4)// 每行显示个数 int
                        .selectionMode(if (true) PictureConfig.SINGLE else PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                        .previewImage(true)// 是否可预览图片 true or false
                        .isCamera(true)// 是否显示拍照按钮 true or false
                        .isZoomAnim(false)// 图片列表点击 缩放效果 默认true
                        .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                        .setOutputCameraPath("/CustomPath")// 自定义拍照保存路径,可不填
                        .enableCrop(false)// 是否裁剪 true or false
                        .compress(true)// 是否压缩 true or false
                        .forResult(PictureConfig.CHOOSE_REQUEST)//结果回调onActivityResult code
            }
            R.id.tv_http -> {
                if (httpData == null) {
                    httpData = ArrayList<ImagePreviewBean>()
                    httpData!!.add(ImagePreviewBean("19"))
                    httpData!!.add(ImagePreviewBean("20"))
                    httpData!!.add(ImagePreviewBean("21"))
                    httpData!!.add(ImagePreviewBean("22"))
                    httpData!!.add(ImagePreviewBean("23"))
                }
                GPreviewBuilder.from(this)
                        .setData(httpData!!)
                        .setUserFragment(BasePhotoFragment::class.java)
                        .setCurrentIndex(0)
                        .setSingleFling(true)
                        .setType(GPreviewBuilder.IndicatorType.Number)
                        .start()
            }
        }
    }

    override fun getContentResId(): Int = R.layout.fragment_mine

    override fun initEvent(view: View) {
        BaseTools.setTitleBar(title, "我的")
        val html = "<font color='#ff0000'>点击-></font><font color='#0000FF'>项目<font>"
        val charSequence = Html.fromHtml(html)
        tv_project.text = charSequence
        tv_project.setOnClickListener(this)
        tv_mayun_project.setOnClickListener(this)
        tv_http.setOnClickListener(this)
        tv_project_description.setOnClickListener(this)
        tv_choice_image.setOnClickListener(this)
        tv_old_work.setOnClickListener(this)
        tv_recyclerview.setOnClickListener(this)
    }

    var mPicFile: File? = null
    var mRequestCode: Int? = 1002

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            PictureConfig.CHOOSE_REQUEST -> {
                if (resultCode == Activity.RESULT_OK) {
                    var path = PictureSelector.obtainMultipleResult(data)[0].path
                    mPicFile = File(activity.cacheDir, UUID.randomUUID().toString() + ".jpg")
                    startActivityForResult(Intent(activity, IMGEditActivity::class.java)
                            .putExtra(IMGEditActivity.EXTRA_IMAGE_URI, Uri.fromFile(File(path)))
                            .putExtra(IMGEditActivity.EXTRA_IMAGE_SAVE_PATH, mPicFile?.absolutePath), mRequestCode!!)
                }
            }
            mRequestCode -> {
                if (resultCode == Activity.RESULT_OK) {
                    BaseTools.load(context, File(mPicFile!!.absolutePath), iv_image)
                }
            }
        }
    }
}
