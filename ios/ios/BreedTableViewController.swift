//
//  BreedTableViewController.swift
//  ios
//
//  Created by Michael Friend on 6/28/21.
//

import UIKit
import shared

class BreedTableViewController: UITableViewController {

    @IBOutlet var breedTableView: UITableView!
    var data: MainScreenData = MainScreenData(dogBreeds: [], showLoading: false)
    lazy var viewModel: NativeMainViewModel = NativeMainViewModel(dogDataUpdate: {[weak self] data in
        self?.screenDataUpdate(data)
    })
    override func viewDidLoad() {
        super.viewDidLoad()
        viewModel.observe()
        breedTableView.delegate = self
        breedTableView.dataSource = self
        
        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false
        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem
    }
    override func viewWillDisappear(_ animated: Bool) {
         viewModel.unobserve()
    }
    // MARK: - Table view data source
    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return data.dogBreeds.count
    }
    func screenDataUpdate(_ data: MainScreenData) {
        self.data = data
        breedTableView.reloadData()
    }
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = breedTableView.dequeueReusableCell(withIdentifier: "BreedCell", for: indexPath)
        // Configure the cell...
        let breed = data.dogBreeds[indexPath.row]
        cell.textLabel?.text = breed.name
        let url = NSURL(string: breed.imageUrl)
        let data = NSData(contentsOf : url! as URL)
        let image = UIImage(data : data! as Data)
        cell.imageView?.image = image
        return cell
    }

}
